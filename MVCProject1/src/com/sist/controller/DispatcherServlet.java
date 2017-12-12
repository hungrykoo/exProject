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

import java.util.*; //MAP (��û=>Ŭ����(��) ��Ī)

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
	 * ���÷��� = Ŭ���������� �о �޸��Ҵ��� �Ѵ�.
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
				// Singleton (init �� ���� �ѹ�������ǰ� �״������ʹ� ����X)
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
			// URI:����ڰ� �ּ��Է¶��� ��û�� ����
			// http://localhost:8080/MVCProject1/list.do
			//URI : /MVCProject1/list.do
			cmd=cmd.substring(request.getContextPath().length()+1, cmd.lastIndexOf("."));
			//��û�� ó�� => �𵨸�����(Ŭ����,�޼ҵ�)
			Model model=(Model)clsMap.get(cmd);
			// model = ���� ���� ������� request�� ��ƴ޶�
			// Call by Reference => �ּҸ� �Ѱ��ְ� �ּҿ� ���� ä���.
			String jsp=model.execute(request);
			//jsp�� request, session ���� ����
			RequestDispatcher rd = request.getRequestDispatcher(jsp);
			rd.forward(request, response);
			// jsp�� _jspService()�� ȣ���Ѵ�.
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
