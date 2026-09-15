package com.auth;

import java.io.PrintWriter;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.websocket.Session;

@WebServlet("/login")
public class LogInServlet extends HttpServlet{
	
	protected void doPost(HttpServletRequest req, HttpServletResponse res) {
		try {
			
			String username = req.getParameter("username");
			String password = req.getParameter("password");
			
			PrintWriter out = res.getWriter();
			
			HttpSession session = req.getSession();
			session.setAttribute("username", username);
			
			Cookie cookie = new Cookie("password", password);
			res.addCookie(cookie);
			
			res.sendRedirect("profile.jsp");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
}
