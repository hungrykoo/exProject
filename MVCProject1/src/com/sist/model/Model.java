package com.sist.model;

import javax.servlet.http.HttpServletRequest;
// 1 .8�̻�  : deauflt�޼ҵ�, static�޼ҵ� => ������ �޼ҵ带 ���� �� �ִ�.
/*
 * 	���
 * 	class / interface
 * 		extends
 * 	class ====== > class
 *			extends
 *  interface ====> interface
 *  		implements
 *  interface ====> class 
 * 	(X)
 *  class ====:> intferface
 * 
 * 
 * 
 */

public interface Model {
	public String execute(HttpServletRequest req) throws Exception;
	
}
