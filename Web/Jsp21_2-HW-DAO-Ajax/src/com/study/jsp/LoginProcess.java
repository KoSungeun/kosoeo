package com.study.jsp;

import java.io.IOException;
import java.io.PrintWriter;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebServlet("/LoginProcess")
public class LoginProcess extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("doGet");
		actionDo(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("doPost");
		actionDo(request, response);
	}
	
	private void actionDo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		response.setContentType("application/json; charset=UTF-8");
		PrintWriter out = response.getWriter();
		HttpSession session = request.getSession();
		
		String id = request.getParameter("id");
		String pw = request.getParameter("pw");
		
		MemberDao dao = MemberDao.getInstance();
		int checkNum = dao.userCheck(id, pw);
		if(checkNum == MemberDao.MEMBER_LOGIN_IS_NOT) {
			out.println("{\"result\": \"fail\","
					+ "\"msg\": \"아이디가 존재하지 않습니다.\"}");	
		} else if(checkNum == MemberDao.MEMBER_LOGIN_PW_NO_GOOD) {
			out.println("{\"result\": \"fail\","
					+ "\"msg\": \"비밀번호가 틀립니다.\"}");	
		} else if(checkNum == MemberDao.MEMBER_LOGIN_SUCCESS) {
			MemberDto dto = dao.getMember(id);
			String name = dto.getName();
			session.setAttribute("id", id);
			session.setAttribute("name", name);
			session.setAttribute("ValidMem", "yes");
			out.println("{\"result\": \"success\","
					+ "\"msg\": \"로그인 성공.\"}");	
		}

	}

}
