package com.kosoeo.command;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;



import com.kosoeo.dao.MemberDAO;
import com.kosoeo.dto.MemberDTO;



public class MemberWithdrawCommand implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		response.setContentType("application/json; charset=UTF-8");
		PrintWriter out = response.getWriter();
		HttpSession session = request.getSession();
		MemberDTO dto = (MemberDTO) session.getAttribute("MemberDTO");
		dto.setPassword(request.getParameter("password"));
		MemberDAO dao = MemberDAO.getInstance();
		
		String result = null;
		String msg = null;
		
		

		if(dao.userCheck(dto.getEmail(), dto.getPassword(), "normal") == MemberDAO.MEMBER_LOGIN_PW_NO_GOOD) {
			result = "fail";
			msg = "비밀 번호가 다릅니다.";
		} else {
			result = "success";
			msg = "탈퇴하였습니다";
			dao.withdraw(dto.getNo());
		}
		
		out.println("{\"result\": \"" + result + "\","
				+ "\"msg\": \"" +  msg + "\"}");	
		
	}

}
