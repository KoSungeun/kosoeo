package com.kosoeo.command;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.JsonObject;
import com.kosoeo.dao.CommentDAO;
import com.kosoeo.dto.Member;



public class CommentDeleteCommand implements Command {

	final int LOGIN_FAIL = 0;
	final int CONTENT_FAIL = -1;
	final int COMMENT_FAIL = -2;
	final int SESSION_FAIL = -3;
	final int SUCEESS = 1;
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		response.setContentType("application/json; charset=UTF-8");
		PrintWriter out = response.getWriter();
		HttpSession session = request.getSession();
		
		int memberNo = 0;
		int commentNo = 0;
		int sessionNo = 0;
		
		Member member = (Member) session.getAttribute("member");
		if(member != null) {
			sessionNo = member.getNo();
		}
		
		int result = SUCEESS;
		String msg = "";
		try {
			memberNo = Integer.parseInt(request.getParameter("memberNo"));
			commentNo = Integer.parseInt(request.getParameter("commentNo"));
		} catch (NumberFormatException e) {
		}
		
		if(sessionNo == 0) {
			result = LOGIN_FAIL;
			msg = "로그인 해주세요.";
		} else if(commentNo == 0) {
			result = COMMENT_FAIL;
			msg = "정상적인 접근이 아닙니다";
		} else if(memberNo != sessionNo) {
			result = SESSION_FAIL;
			msg = "글쓴이가 아닙니다";
		}

		if(result == SUCEESS) {
			CommentDAO dao = CommentDAO.getInstance();
			dao.delete(commentNo);
		}
		JsonObject json = new JsonObject();
		json.addProperty("result", result);
		json.addProperty("msg", msg);
		out.println(json);
		
	}

}
