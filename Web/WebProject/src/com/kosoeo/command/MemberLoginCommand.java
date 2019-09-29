package com.kosoeo.command;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import com.kosoeo.dao.MemberDAO;
import com.kosoeo.dto.Member;



public class MemberLoginCommand implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		response.setContentType("application/json; charset=UTF-8");
		PrintWriter out = response.getWriter();
		HttpSession session = request.getSession();
		
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		String name = request.getParameter("name");
		String nickName = request.getParameter("nickName");
		String type = request.getParameter("type"); 
		
		MemberDAO dao = MemberDAO.getInstance();
		
		int checkNum = dao.userCheck(email, password, type);
		if(checkNum == MemberDAO.MEMBER_LOGIN_IS_NOT && type.equals("normal")) {
			out.println("{\"result\": \"-1\","
					+ "\"msg\": \"이메일이 존재하지 않습니다.\"}");	
		} else if(checkNum == MemberDAO.MEMBER_LOGIN_IS_NOT && !type.equals("normal")) {
			Member dto = new Member();
			dto.setEmail(email);
			dto.setName(name);
			if(!nickName.trim().equals("undefined")) {
				dto.setNickName(nickName);
			} else {
				dto.setNickName(name);
			}
			dao.join(dto);
			dto = dao.getMember(email);
			session.setAttribute("member", dto);
			out.println("{\"result\": \"1\","
					+ "\"msg\": \"로그인 성공.\"}");
		} else if(checkNum == MemberDAO.MEMBER_LOGIN_PW_NO_GOOD) {
			out.println("{\"result\": \"0\","
					+ "\"msg\": \"비밀번호가 틀립니다.\"}");	
		} else if(checkNum == MemberDAO.MEMBER_LOGIN_SUCCESS) {
			Member dto = dao.getMember(email);
			session.setAttribute("member", dto);
			out.println("{\"result\": \"1\","
					+ "\"msg\": \"로그인 성공.\"}");	
		}
		
	}

}
