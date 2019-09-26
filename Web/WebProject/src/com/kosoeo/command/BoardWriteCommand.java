package com.kosoeo.command;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kosoeo.dao.BoardDAO;



public class BoardWriteCommand implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String name = request.getParameter("name");
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		
		System.out.println(content);
		
		int category = 0;
		try {
			category = Integer.parseInt(request.getParameter("category"));
		} catch (NumberFormatException e) {
			// TODO: handle exception
		}
		
		BoardDAO dao = new BoardDAO();
		dao.write(name, title, content, category);
	}

}
