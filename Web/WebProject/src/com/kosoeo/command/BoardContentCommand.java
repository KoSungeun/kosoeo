package com.kosoeo.command;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kosoeo.dao.BoardDAO;
import com.kosoeo.dto.Board;



public class BoardContentCommand implements Command{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int no = 0;
		try {
			no = Integer.parseInt(request.getParameter("no"));
		} catch (NumberFormatException e) {
			
		}
		BoardDAO cdao = new BoardDAO();
		Board cdto = cdao.contentView(no);
		
		request.setAttribute("content_view", cdto);

	}

}
