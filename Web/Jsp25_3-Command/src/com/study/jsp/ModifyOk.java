package com.study.jsp;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.jasper.runtime.JspRuntimeLibrary;

public class ModifyOk implements Service {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		MemberDto dto = new MemberDto();
		JspRuntimeLibrary.introspect(dto, request);
		HttpSession session = request.getSession();
		String id = (String)session.getAttribute("id");
		dto.setId(id);
		MemberDao dao = MemberDao.getInstance();
		
		int ri = dao.updateMember(dto);
		

		if(ri == 1) {
			out.println("<html><head></head><body>");
			out.println("<script>");
			out.println("	alert(\"정보가 수정되었습니다.\");");
			out.println("	document.location.href=\"main.jsp\";");
			out.println("</script>");
			out.println("</body></html>");

		} else {
			out.println("<html><head></head><body>");
			out.println("<script>");
			out.println("	alert(\"정보수정에 실패했습니다.\");");
			out.println("	history.go(-1);");
			out.println("</script>");
			out.println("</body></html>");
			
		}
	}

}
