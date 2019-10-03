package com.kosoeo.command;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kosoeo.dao.MemberDAO;

public class MemberEmailCheckCommand implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		
		String email = request.getParameter("email");
		MemberDAO dao = MemberDAO.getInstance();
		response.setContentType("application/json; charset=UTF-8");
		PrintWriter out = response.getWriter();

		String result = null;
		String msg = null;
		if(dao.emailCheck(email) == MemberDAO.MEMBER_EXISTENT) {
			result = "fail";
			msg = "사용중인 이메일입니다.";
		} else {
			result = "success";
			msg = "사용가능한 이메일입니다.";
		}
		
		out.println("{\"result\": \"" + result + "\","
				+ "\"msg\": \"" +  msg + "\"}");		
	}

}
