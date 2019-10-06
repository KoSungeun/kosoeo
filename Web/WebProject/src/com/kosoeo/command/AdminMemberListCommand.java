package com.kosoeo.command;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.kosoeo.dao.MemberDAO;
import com.kosoeo.dto.Member;
import com.kosoeo.dto.Page;

public class AdminMemberListCommand implements Command{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int nPage = 1;
		boolean isAdmin = false;
		HttpSession session = request.getSession();
		Member member = (Member) session.getAttribute("member");
		String type = request.getParameter("type");
		String word = request.getParameter("word");
		try {
			nPage = Integer.parseInt(request.getParameter("page"));
		} catch (Exception e) {}
		
		if(type != null) {
			if(type.equals("email") || type.equals("name") || type.equals("nickName")) {
				word = "'%"+ word + "%'";
			} else {
				type = null;
			}
		}
		
		if(member != null) {
			isAdmin = member.isAdmin();
		}
		
		if(isAdmin) {
			MemberDAO dao = new MemberDAO();
			Page pInfo = dao.articlePage(nPage, type, word);
			request.setAttribute("page", pInfo);
			nPage = pInfo.getCurPage();
			session.setAttribute("cpage", nPage);
			request.setAttribute("list", dao.list(nPage, type, word));
		}
		request.setAttribute("isAdmin", isAdmin);
	}

}
