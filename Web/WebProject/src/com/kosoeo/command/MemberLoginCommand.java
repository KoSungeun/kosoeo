package com.kosoeo.command;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.JsonObject;
import com.kosoeo.dao.MemberDAO;
import com.kosoeo.dto.Member;




public class MemberLoginCommand implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("application/json; charset=UTF-8");
		PrintWriter out = response.getWriter();
		HttpSession session = request.getSession();
		
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		String name = request.getParameter("name");
		String nickName = request.getParameter("nickName");
		String type = request.getParameter("type"); 
		
		JsonObject json = new JsonObject();

		
		MemberDAO dao = MemberDAO.getInstance();
		
		int checkNum = dao.userCheck(email, password, type);
		String msg = "";
		
		if(checkNum == MemberDAO.MEMBER_LOGIN_IS_NOT && type.equals("normal")) {
			msg = "이메일이 존재하지 않습니다.";
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
			checkNum = MemberDAO.MEMBER_LOGIN_SUCCESS;
			msg = "로그인 성공.";
		} else if(checkNum == MemberDAO.MEMBER_LOGIN_PW_NO_GOOD) {
			msg = "비밀번호가 틀립니다.";
		} else if(checkNum == MemberDAO.MEMBER_LOGIN_SUCCESS) {
			Member dto = dao.getMember(email);
			if(dao.blockCheck(dto.getNo())) {
				checkNum = MemberDAO.MEMBER_LOGIN_BLOCK;
				msg = "정지된 사용자입니다";
			} else {
				session.setAttribute("member", dto);
				msg = "로그인 성공.";
			}

		}
		
		json.addProperty("result", checkNum);
		json.addProperty("msg", msg);
		out.println(json);
	}

}
