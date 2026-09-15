package com.auth;


import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/logout")
public class LogOutServlet extends HttpServlet {
	
	protected void doPost(HttpServletRequest req, HttpServletResponse res) {
		try {
			HttpSession session = req.getSession();
			session.removeAttribute("username");
			
			Cookie cookie = new Cookie("password", "");
			res.addCookie(cookie);
			
			res.sendRedirect("profile.jsp");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
