package com.sist.model;

import javax.servlet.http.HttpServletRequest;
// 1 .8이상  : deauflt메소드, static메소드 => 구현된 메소드를 만들 수 있다.
/*
 * 	상속
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
