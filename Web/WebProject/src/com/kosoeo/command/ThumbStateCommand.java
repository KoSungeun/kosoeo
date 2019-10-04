package com.kosoeo.command;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;
import com.kosoeo.dao.ThumbDAO;
import com.kosoeo.dto.Member;
import com.kosoeo.dto.Thumb;

public class ThumbStateCommand implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("application/json; charset=UTF-8");
		
		PrintWriter out = response.getWriter();
		HttpSession session = request.getSession();
		int boardNo = 0;
		int memberNo = 0;
		
		Member member = (Member) session.getAttribute("member");
		
		if(member != null) {
			memberNo = member.getNo();
		}
		
		try {
			boardNo = Integer.parseInt(request.getParameter("no"));
		} catch (NumberFormatException e) {
		}
		
		ThumbDAO dao = null;
		Thumb thumb = null;
		if(boardNo != 0) {
			dao = new ThumbDAO();
			thumb = dao.upDownCount(boardNo, memberNo);
			request.setAttribute("thumb", thumb);
		}

		if(memberNo != 0) {
			out.println(new Gson().toJson(thumb));
		}
	}
}
