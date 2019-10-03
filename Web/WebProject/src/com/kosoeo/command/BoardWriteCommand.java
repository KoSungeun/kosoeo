package com.kosoeo.command;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kosoeo.dao.BoardDAO;



public class BoardWriteCommand implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		
		
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		
		int memberNo = 0;
		int category = 0;
		try {
			category = Integer.parseInt(request.getParameter("category"));
			memberNo = Integer.parseInt(request.getParameter("memberNo"));
		} catch (NumberFormatException e) {
			// TODO: handle exception
		}
		
		BoardDAO dao = new BoardDAO();
		request.setAttribute("seq", dao.write(memberNo, title, content, category));
	}

}
