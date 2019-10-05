package com.kosoeo.command;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.jasper.runtime.JspRuntimeLibrary;

import com.kosoeo.dao.BoardDAO;
import com.kosoeo.dto.Board;
import com.kosoeo.dto.Member;


public class BoardReplyCommand implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Board board = new Board();
		Member member = new Member();
		
		int memberNo = 0;
		
		try {
			memberNo = Integer.parseInt(request.getParameter("memberNo"));
		} catch (Exception e) {}
		
		member.setNo(memberNo);
		JspRuntimeLibrary.introspect(board, request);
		board.setMember(member);
		BoardDAO dao = new BoardDAO();
		dao.reply(board);
	}

}
