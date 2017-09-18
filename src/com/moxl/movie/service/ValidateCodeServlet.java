package com.moxl.movie.service;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class ValidateCodeServlet extends HttpServlet{
		@Override
		protected void doGet(HttpServletRequest request, HttpServletResponse response)
				throws ServletException, IOException {
			request.setCharacterEncoding("UTF-8");
			response.setContentType("image/jpeg");
			ServletOutputStream out = response.getOutputStream();
			int width = 60;
			int height = 20;
			BufferedImage imgbuf = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
			Graphics2D g = imgbuf.createGraphics();
			//
			g.setColor(getRandColor(200, 250));
			//
			g.fillRect(0, 0, width, height);
			//
			Random r = new Random();
			g.setColor(getRandColor(160, 200));
			
			for(int i = 0 ;i < 100;i++){
				int x = r.nextInt(width);
				int y = r.nextInt(height);
				int x1 = r.nextInt(12);
				int y1 = r.nextInt(12);
				g.drawLine(x, y, x+x1, y+y1);
			}
			
			g.setColor(getRandColor(120, 240));
			for(int i = 0 ; i < 100;i++){
				int x = r.nextInt(width);
				int y = r.nextInt(height);
				g.drawOval(x, y, 0, 0);
			}
			//
			g.setFont(new Font("Times New Roman",Font.PLAIN,18));
			String code = "";
			for(int i = 0 ;i < 4;i++){
				String rand = String.valueOf(r.nextInt(10));
				code += rand;
				g.setColor(new Color(20+r.nextInt(110),20+r.nextInt(110),20+r.nextInt(110)));
				g.drawString(rand, 13*i+6, 16);
			}
			HttpSession session = request.getSession();
			session.setAttribute("code", code);
			//
			ImageIO.write(imgbuf, "JPEG", out);
			out.close();
		}
		@Override
		protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		}
		private Color getRandColor(int fc,int bc){
			Random random = new Random();
			if(fc>255)
				fc = 255;
			if(fc<0)
				fc = 0;
			if(bc>255)
				bc = 255;
			if(bc<0)
				bc = 0;
			int r = fc + random.nextInt(bc - fc);
			int g = fc + random.nextInt(bc - fc);
			int b = fc + random.nextInt(bc - fc);
			return new Color(r,g,b);
			
		}
}

