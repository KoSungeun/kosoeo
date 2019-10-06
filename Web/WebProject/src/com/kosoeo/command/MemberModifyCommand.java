package com.kosoeo.command;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.jasper.runtime.JspRuntimeLibrary;

import com.kosoeo.dao.MemberDAO;
import com.kosoeo.dto.Member;



public class MemberModifyCommand implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("application/json; charset=UTF-8");
		PrintWriter out = response.getWriter();
		HttpSession session = request.getSession();
		Member dto = (Member) session.getAttribute("member");
		JspRuntimeLibrary.introspect(dto, request);
		MemberDAO dao = MemberDAO.getInstance();
		
		String result = null;
		String msg = null;
		
		int ri = dao.modify(dto);

		if(ri < 1) {
			result = "fail";
			msg = "정보수정에 실패했습니다.";
		} else {
			result = "success";
			msg = "정보가 수정되었습니다";
		}
		session.setAttribute("modalMsg", msg);
		out.println("{\"result\": \"" + result + "\","
				+ "\"msg\": \"" +  msg + "\"}");	
		
	}

}
