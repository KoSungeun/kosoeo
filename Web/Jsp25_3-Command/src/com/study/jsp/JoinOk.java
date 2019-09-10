package com.study.jsp;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.jasper.runtime.JspRuntimeLibrary;

public class JoinOk implements Service {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		MemberDto dto = new MemberDto();
		JspRuntimeLibrary.introspect(dto, request);
		HttpSession session = request.getSession();
		dto.setrDate(new Timestamp(System.currentTimeMillis()));
		MemberDao dao = MemberDao.getInstance();
		
		if(dao.confirmId(dto.getId()) == MemberDao.MEMBER_EXISTENT) {
			out.println("<html><head></head><body>");
			out.println("<script>");
			out.println("	alert(\"아이디가 이미 존재 합니다.\");");
			out.println("	history.back();");
			out.println("</script>");
			out.println("</body></html>");
		} else {
			int ri = dao.insertMember(dto);
			if(ri == MemberDao.MEMBER_JOIN_SUCCESS) {
				session.setAttribute("id", dto.getId());
				
				out.println("<html><head></head><body>");
				out.println("<script>");
				out.println("	alert(\"회원가입을 축하 합니다.\");");
				out.println("	document.location.href=\"login.jsp\";");
				out.println("</script>");
				out.println("</body></html>");
			} else {
				out.println("<html><head></head><body>");
				out.println("<script>");
				out.println("	alert(\"회원가입에 실패했습니다.\");");
				out.println("	history.back();");
				out.println("</script>");
				out.println("</body></html>");	
			}
		}
		out.close();	
	}

}
