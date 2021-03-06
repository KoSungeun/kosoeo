package com.kosoeo.command;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.kosoeo.dao.MemberDAO;


public class AdminMemberWithdrawCommand implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int memberNo = Integer.parseInt(request.getParameter("memberNo"));
		MemberDAO dao = new MemberDAO();
		dao.withdraw(memberNo);
		
		HttpSession session = request.getSession();
		session.setAttribute("msg", "탈퇴시켰습니다.");
	}

}
