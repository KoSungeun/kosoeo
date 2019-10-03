package com.kosoeo.command;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.jasper.runtime.JspRuntimeLibrary;

import com.kosoeo.dao.MemberDAO;
import com.kosoeo.dto.Member;


public class MemberJoinCommand implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("application/json; charset=UTF-8");
		PrintWriter out = response.getWriter();
		Member dto = new Member();
		JspRuntimeLibrary.introspect(dto, request);
		MemberDAO dao = MemberDAO.getInstance();
		String result = null;
		String msg = null;
		if(dao.emailCheck(dto.getEmail()) == MemberDAO.MEMBER_EXISTENT) {
			result = "fail";
			msg = "사용중인 이메일입니다";
		} else {
			dao.join(dto);
			result = "success";
			msg = "회원가입 성공";
		}
		out.println("{\"result\": \"" + result + "\","
				+ "\"msg\": \"" +  msg + "\"}");	
	}

}
