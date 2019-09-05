package com.study.jsp01;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet(urlPatterns={"/ServletInitParam2"},
			initParams={@WebInitParam(name="id", value="abcdef"),
						@WebInitParam(name="pw", value="1234"),
						@WebInitParam(name="path", value="C:\\java\\workspace")})
public class ServletInitParam2 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = getInitParameter("id");
		String pw = getInitParameter("pw");
		String path = getInitParameter("path");
				
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
		writer.println("</body>");
		writer.println("</html>");
	}

}
