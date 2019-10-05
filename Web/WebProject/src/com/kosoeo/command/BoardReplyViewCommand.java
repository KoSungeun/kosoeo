package com.kosoeo.command;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kosoeo.dao.BoardDAO;
import com.kosoeo.dto.Board;

public class BoardReplyViewCommand implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String boardNo = request.getParameter("boardNo");
		BoardDAO dao = new BoardDAO();
		Board board = dao.replyView(boardNo);
		request.setAttribute("reply_view", board);
	}

}
