package com.sist.controller;

import java.io.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sist.model.Model;

import java.util.*; //MAP (요청=>클래스(모델) 매칭)

public class DispatcherServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private String [] strCls= {
			"com.sist.model.MovieList",
			"com.sist.model.MovieDetail"
	};
	private String [] strCmd= {
			"list","detail"
	};
	//<bean id="list" class="com.sist.model.MovieList" />
	// csv => list,com.sist.model.MovieList
	/*
	 * 리플렉션 = 클레스정보를 읽어서 메모리할당을 한다.
	 * key       value
	 * list   new MovieList()  		= Class.forName()
	 * detail new MovieDetail()
	 * 
	 */ 
	
	private Map clsMap = new HashMap();
	// HashMap, Hashtable
	public void init(ServletConfig config) throws ServletException {
		try 
		{
			for(int i=0; i<strCls.length; i++)
			{
				Class clsName =Class.forName(strCls[i]);
				Object obj=clsName.newInstance();
				clsMap.put(strCmd[i], obj);
				// Singleton (init 은 최초 한번만실행되고 그다음부터는 실행X)
			}
			// clsMap.put("list", new MovieList());
			// clsMap.put("detail", new MovieDetail());
		}catch(Exception ex)
		{
			System.out.println(ex.getMessage());
		}
	}

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try
		{
			// list.do, detail.do
			String cmd=request.getRequestURI();
			// URI:사용자가 주소입력란에 요청한 파일
			// http://localhost:8080/MVCProject1/list.do
			//URI : /MVCProject1/list.do
			cmd=cmd.substring(request.getContextPath().length()+1, cmd.lastIndexOf("."));
			//요청을 처리 => 모델를래스(클래스,메소드)
			Model model=(Model)clsMap.get(cmd);
			// model = 실행 한후 결과값을 request에 담아달라
			// Call by Reference => 주소를 넘겨주고 주소에 값을 채운다.
			String jsp=model.execute(request);
			//jsp에 request, session 값을 전송
			RequestDispatcher rd = request.getRequestDispatcher(jsp);
			rd.forward(request, response);
			// jsp의 _jspService()를 호출한다.
			/*
			 * 
			 * service (request, response)
			 * {
			 * 		_jspService(request);
			 * }
			 */
		}catch (Exception ex)
		{
			System.out.println(ex.getMessage());
		}
	}

}
