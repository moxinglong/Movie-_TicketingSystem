package com.moxl.movie.action;

import java.util.Properties;


import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import com.moxl.movie.pojo.User;

public class SendMail extends Thread{
	
	//用于发送邮箱的邮箱账号
	private String from     = "15673841612@163.com";
	
	//邮箱的用户名
	private String username = "15673841612@163.com";
	
	//邮箱密码
	private String password = "wushuxuan195525";
	
	//发送邮箱的服务器地址
	private String host     = "smtp.163.com";
	
	private String code;
	
	private User user;
	
	public SendMail(User user,String code){
		this.user = user;
		this.code = code;
	}
	@Override
	public void run() {
		try{
				Properties prop = new Properties();
												
				prop.setProperty("mail.host", host);
				
				prop.setProperty("mail.transport.protocol", "smtp");
				
				prop.setProperty("mail.smtp.auth", "true");
				
				Session session = Session.getInstance(prop);
				
				//session.setDebug(true);
				
				Transport ts = session.getTransport();
				
				ts.connect(host, username, password);
				
				Message message = createEmail(session,user,code);
				
				ts.sendMessage(message, message.getAllRecipients());
				
				ts.close();
			}catch (Exception e) {
			throw new RuntimeException(e);
			}
	}
	public Message createEmail(Session session,User user,String code){
		
		MimeMessage message = new MimeMessage(session);
		
		try {
			message.setFrom(new InternetAddress(from));

			message.setRecipient(Message.RecipientType.TO, new InternetAddress(user.getEmail()));
			
			message.setSubject("请勿回复此邮件");
			
			String info = "您的注册码为："+code;

			message.setContent(info, "text/html;charset=UTF-8");
			
			message.saveChanges();
			
		} catch (AddressException e) {
			
			e.printStackTrace();
		} catch (MessagingException e) {
			
			e.printStackTrace();
		}
			return message;
	}
}
