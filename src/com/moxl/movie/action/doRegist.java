package com.moxl.movie.action;

import java.io.IOException;

import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.moxl.movie.nutz.BaseSrv;
import com.moxl.movie.nutz.UserModule;

public class doRegist extends HttpServlet{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		HttpSession session = request.getSession();
		PrintWriter out = response.getWriter();
		try {
		String email = request.getParameter("email");
		String xm = request.getParameter("xm");
		String pwd 	 = BaseSrv.lrwCode(request.getParameter("registpassword"),"");
		String sex	 = request.getParameter("sex");
		String role = "5";

		String sql = "insert into user(email,xm,pwd,sex,role) values('" + email+ "','" + xm+ "','" + pwd+ "','"+sex+"','"+role+"')";
		
		int rs = UserModule.ExecuteUpdate(sql);
		if(rs == 1){
			session.setAttribute("username", email);
			out.print("<script type='text/javascript'>");
			out.print("alert('注册成功，已为您自动登录！');");
			out.print("window.location.href='"+request.getContextPath()+"/jsp/loginsuccess.jsp';");
			out.print("</script>");
		}
		
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doGet(request, response);
	}
}
