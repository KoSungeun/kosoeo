package com.kosoeo.command;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kosoeo.dao.CommentDAO;

public class CommentWriteCommand implements Command {

	final int LOGIN_FAIL = 0;
	final int CONTENT_FAIL = -1;
	final int BOARD_FAIL = -2;
	final int SUCEESS = 1;
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		response.setContentType("application/json; charset=UTF-8");
		PrintWriter out = response.getWriter();
		
		int memberNo = 0;
		int boardNo = 0;
		int result = SUCEESS;
		String msg = "";
		try {
			memberNo = Integer.parseInt(request.getParameter("memberNo"));
			boardNo = Integer.parseInt(request.getParameter("boardNo"));
		} catch (NumberFormatException e) {
		}
		String content = request.getParameter("content");
		if(memberNo == 0) {
			result = LOGIN_FAIL;
			msg = "로그인 해주세요.";
		} else if(boardNo == 0) {
			result = BOARD_FAIL;
			msg = "정상적인 접근이 아닙니다";
		} else if(content.trim().length() <= 0) {
			result = CONTENT_FAIL;
			msg = "내용을 입력해주세요.";
		}

		if(result == SUCEESS) {
			CommentDAO dao = CommentDAO.getInstance();
			dao.write(memberNo, boardNo, content);
		}
		
		out.println("{\"result\": \"" + result + "\","
				+ "\"msg\": \"" +  msg + "\"}");	
	}
	
}
