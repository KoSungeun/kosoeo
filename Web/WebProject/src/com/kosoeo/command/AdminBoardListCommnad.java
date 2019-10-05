package com.kosoeo.command;

import java.io.IOException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.kosoeo.dao.BoardDAO;
import com.kosoeo.dto.Board;
import com.kosoeo.dto.Page;

public class AdminBoardListCommnad implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int nPage = 1;
		int category = 0;
		String type = request.getParameter("type");
		String word = request.getParameter("word");
		

		try {
			nPage = Integer.parseInt(request.getParameter("page"));
		} catch (Exception e) {}
		try {
			category = Integer.parseInt(request.getParameter("category"));
		} catch (Exception e) {}
	
		if(type != null) {
			if(type.equals("title") || type.equals("nickName") || type.equals("content") ) {
				word = "'%"+ word + "%'";
			} else {
				type = null;
			}
		}


		BoardDAO dao = BoardDAO.getInstance();
		Page pInfo = dao.articlePage(nPage, category, type, word);
		request.setAttribute("page", pInfo);
		
		nPage = pInfo.getCurPage();
		
		HttpSession session = null;
		session = request.getSession();
		session.setAttribute("cpage", nPage);
		session.setAttribute("ccategory", category);
		
		
		request.setAttribute("yesterday", Timestamp.valueOf(LocalDateTime.now().minusDays(1)));
		
		ArrayList<Board> dtos = dao.list(nPage, category, type, word);
		

		
		request.setAttribute("list", dtos);
	}

}
