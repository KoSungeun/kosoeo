package com.kosoeo.command;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.jasper.runtime.JspRuntimeLibrary;

import com.kosoeo.dao.BoardDAO;
import com.kosoeo.dto.Board;

public class BoardUpdateCommand implements Command{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		
		Board board = new Board();
		JspRuntimeLibrary.introspect(board, request);
		
		BoardDAO dao = new BoardDAO();
		dao.update(board);
		
	}

	
}
