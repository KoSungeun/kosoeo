package com.kosoeo.command;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.kosoeo.dao.CommentDAO;


public class CommentListCommand implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		response.setContentType("application/json; charset=UTF-8");
		
		PrintWriter out = response.getWriter();
		
		int boardNo = 0;
		try {
			boardNo = Integer.parseInt(request.getParameter("boardNo"));
		} catch (NumberFormatException e) {
		}
				
		CommentDAO dao = CommentDAO.getInstance();
		out.println(new Gson().toJson(dao.list(boardNo)));	
	
	}

}
