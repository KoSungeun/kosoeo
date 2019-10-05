package com.kosoeo.command;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.JsonObject;
import com.kosoeo.dao.BoardDAO;
import com.kosoeo.dto.Member;

public class BoardDeleteCommand implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		response.setContentType("application/json; charset=UTF-8");
		PrintWriter out = response.getWriter();
		HttpSession session = request.getSession();
		
		int memberNo = 0;
		int boardNo = 0;
		int sessionNo = 0;
		
		Member member = (Member) session.getAttribute("member");
		if(member != null) {
			sessionNo = member.getNo();
		}
		
		int result = SUCEESS;
		String msg = "";
		try {
			memberNo = Integer.parseInt(request.getParameter("memberNo"));
			
		} catch (NumberFormatException e) {}
		
		try {
			boardNo = Integer.parseInt(request.getParameter("boardNo"));
		} catch (NumberFormatException e) {
			
		}
		if(sessionNo == 0) {
			result = LOGIN_FAIL;
			msg = "로그인 해주세요.";
		} else if(boardNo == 0) {
			result = PARAM_FAIL;
			msg = "정상적인 접근이 아닙니다";
		} else if(memberNo != sessionNo) {
			result = SESSION_FAIL;
			msg = "글쓴이가 아닙니다";
		}

		if(result == SUCEESS) {
			BoardDAO dao = new BoardDAO();
			dao.delete(boardNo);
			msg = "삭제 됐습니다.";
		}
		
		String action = (String) session.getAttribute("action");
		int curPage = (Integer) session.getAttribute("cpage");
		
		
		JsonObject json = new JsonObject();
		json.addProperty("result", result);
		json.addProperty("msg", msg);
		json.addProperty("location", action + "?page=" +curPage );
		out.println(json);
	}

}
