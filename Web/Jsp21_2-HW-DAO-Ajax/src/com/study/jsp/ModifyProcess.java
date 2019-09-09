package com.study.jsp;

import java.io.IOException;
import java.io.PrintWriter;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.PageContext;

import org.apache.jasper.runtime.JspRuntimeLibrary;


@WebServlet("/ModifyProcess")
public class ModifyProcess extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	MemberDto dto = null;
	PageContext pc = null;
	
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
		dto = new MemberDto();
		JspRuntimeLibrary.introspect(dto, request);
		HttpSession session = request.getSession();
		String id = (String)session.getAttribute("id");
		dto.setId(id);
		MemberDao dao = MemberDao.getInstance();
		
		int ri = dao.updateMember(dto);
		

		if(ri == 1) {
			out.println("{\"result\": \"success\","
					+ "\"msg\": \"정보가 수정되었습니다.\"}");	

		} else {
			out.println("{\"result\": \"fail\","
					+ "\"msg\": \"정보수정에 실패했습니다.\"}");	
		}
	}

}
