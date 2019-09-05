package com.study.jsp02;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/ContextInitParam")
public class ContextInitParam extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = getServletContext().getInitParameter("database");
		String pw = getServletContext().getInitParameter("connect");
		String path = getServletContext().getInitParameter("path2");
				
		response.setContentType("text/html; charset=UTF-8");
		
		PrintWriter writer = response.getWriter();
		
		writer.println("<!DOCTYPE html>");
		writer.println("<html>");
		writer.println("<head>");
		writer.println("<meta charset=\"UTF-8\">");
		writer.println("<title>FormEx</title>");
		writer.println("</head>");
		writer.println("<body>");
		writer.println("아이디 : " + id + "<br>");
		writer.println("비밀번호 :" + pw + "<br>");
		writer.println("패스 : " + path + "<br>");
		writer.println(request.getSession().getServletContext().getAttribute("schedule"));
		writer.println("</body>");
		writer.println("</html>");
	}

}
