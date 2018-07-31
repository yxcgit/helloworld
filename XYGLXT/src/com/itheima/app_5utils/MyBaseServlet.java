package com.itheima.app_5utils;

import java.io.IOException;
import java.lang.reflect.Method;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/*
 * 这是我们自己抽取的BaseServlet,用于让其他的servlet继承,继承后,可以让其他的servlet直接执行想执行的方法
 * 而不必再写doGet和doPost方法
 */
public class MyBaseServlet extends HttpServlet{
	//重写HttpServlet中的service方法,将来tomcat服务器会自动调用这个service方法
	public void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//1:获取用户想执行的方法名
		String p = req.getParameter("m");
		//2:要根据方法名p从子类中反射出p这个方法对象
		Class<? extends MyBaseServlet> c = this.getClass();//获取的是子类的字节码文件对象
		try {
			//3:从c中反射p方法
			Method m = c.getMethod(p,HttpServletRequest.class,HttpServletResponse.class);
			//4:让m这个方法对象执行
			String s = (String)m.invoke(this, req,resp);
			//5:要求子类,如果想进行转发,直接返回转发的路径即可,使用这个工具类帮子类转发
			if(s!=null){
				//有路径,要转发
				req.getRequestDispatcher(s).forward(req, resp);
			}
		} catch (Exception e) {
			e.printStackTrace();
			resp.getWriter().println("请,务必填写您想执行的方法名...形式:Servlet?m=xxx!或检查程序异常!");
		} 
	}
}
