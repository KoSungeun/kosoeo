package com.kosoeo.command;

import java.io.IOException;
import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.kosoeo.dao.BoardDAO;
import com.kosoeo.dto.BoardDTO;
import com.kosoeo.dto.BoardPage;



public class BoardListCommand implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int nPage = 1;
		int category = 0;
		String type = request.getParameter("type");
		String word = request.getParameter("word");
		

		try {
			nPage = Integer.parseInt(request.getParameter("page"));
		} catch (Exception e) {}

		category = (int) request.getAttribute("category");
		
		if(type != null) {
			if(type.equals("title") || type.equals("name") || type.equals("content") ) {
				request.setAttribute("type", type);
				request.setAttribute("word", word);
				word = "'%"+ word + "%'";
			} else {
				type = null;
			}
		}


		BoardDAO dao = BoardDAO.getInstance();
		BoardPage pInfo = dao.articlePage(nPage, category, type, word);
		request.setAttribute("page", pInfo);
		
		nPage = pInfo.getCurPage();
		
		HttpSession session = null;
		session = request.getSession();
		session.setAttribute("cpage", nPage);
		session.setAttribute("ccategory", category);

		String action = "";
		if(category == 0) {
			action = "notice";
		} else if(category == 1) {
			action = "free";
		} else if(category == 2) {
			action = "down";
		}
		
		
		request.setAttribute("yesterday", Timestamp.valueOf(LocalDateTime.now().minusDays(1)));

		request.setAttribute("action", action);
		ArrayList<BoardDTO> dtos = dao.list(nPage, category, type, word);
		

		
		request.setAttribute("list", dtos);
	}
	
}
