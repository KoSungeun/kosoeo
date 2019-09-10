package com.study.jsp;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginOk implements Service {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		HttpSession session = request.getSession();
		
		String id = request.getParameter("id");
		String pw = request.getParameter("pw");
		
		MemberDao dao = MemberDao.getInstance();
		int checkNum = dao.userCheck(id, pw);
		if(checkNum == MemberDao.MEMBER_LOGIN_IS_NOT) {
			out.println("<html><head></head><body>");
			out.println("<script>");
			out.println("	alert(\"아이디가 존재하지 않습니다.\");");
			out.println("	history.back();");
			out.println("</script>");
			out.println("</body></html>");
		} else if(checkNum == MemberDao.MEMBER_LOGIN_PW_NO_GOOD) {
			out.println("<html><head></head><body>");
			out.println("<script>");
			out.println("	alert(\"비밀번호가 틀립니다.\");");
			out.println("	history.back();");
			out.println("</script>");
			out.println("</body></html>");
		} else if(checkNum == MemberDao.MEMBER_LOGIN_SUCCESS) {
			MemberDto dto = dao.getMember(id);
			String name = dto.getName();
			session.setAttribute("id", id);
			session.setAttribute("name", name);
			session.setAttribute("ValidMem", "yes");
			
			response.sendRedirect("main.jsp");	
		}
	}

}
