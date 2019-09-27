package com.kosoeo.command;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kosoeo.dao.BoardDAO;
import com.kosoeo.dto.BoardDTO;


public class BoardContentCommand implements Command{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String no = request.getParameter("no");
		BoardDAO dao = new BoardDAO();
		BoardDTO dto = dao.contentView(no);
		
		request.setAttribute("content_view", dto);
	}

}
