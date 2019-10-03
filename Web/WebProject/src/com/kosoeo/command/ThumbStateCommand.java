package com.kosoeo.command;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.kosoeo.dao.CommentDAO;
import com.kosoeo.dao.ThumbDAO;
import com.kosoeo.dto.Thumb;

public class ThumbStateCommand implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("application/json; charset=UTF-8");
		
		PrintWriter out = response.getWriter();
		
		int boardNo = 0;
		int memberNo = 0;
		try {
			boardNo = Integer.parseInt(request.getParameter("boardNo"));
			memberNo = Integer.parseInt(request.getParameter("MemberNo"));
		} catch (NumberFormatException e) {
		}
				
		ThumbDAO thumb = new ThumbDAO();
		out.println(new Gson().toJson(thumb.upDownCount(boardNo, memberNo)));	
	
	}

}
