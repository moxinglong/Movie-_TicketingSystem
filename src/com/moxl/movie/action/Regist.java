package com.moxl.movie.action;

import java.io.IOException;

import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.moxl.movie.pojo.User;

public class Regist extends HttpServlet{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
	try{			
		String email    = request.getParameter("email");
		
		User user = new User();
		user.setEmail(email);
		
		String code = "";
		
		
		Random r = new Random();
		for(int i = 0 ;i < 4;i++){			
			String rand = String.valueOf(r.nextInt(10));
			code += rand;	
		}
		response.getWriter().write(code);
		
		SendMail send = new SendMail(user,code);
		
		send.start();
	}catch(Exception e){
		e.printStackTrace();
	}
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {		
		this.doGet(request, response);
	}
}
