package com.kosoeo.command;

import java.io.IOException;
import java.util.ArrayList;

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
		int type = 0;
		String word = request.getParameter("word");
		try {
			nPage = Integer.parseInt(request.getParameter("page"));
		} catch (Exception e) {}
		try {
			category = Integer.parseInt(request.getParameter("category"));
		} catch (Exception e) {}
		try {
			type = Integer.parseInt(request.getParameter("type"));
		} catch (Exception e) {}
		
		

		BoardDAO dao = BoardDAO.getInstance();
		BoardPage pInfo = dao.articlePage(nPage, category);
		request.setAttribute("page", pInfo);
		
		nPage = pInfo.getCurPage();
		
		HttpSession session = null;
		session = request.getSession();
		session.setAttribute("cpage", nPage);
		session.setAttribute("ccategory", category);

		if(type > 0) {
			
		} else {
			
		}
		
		ArrayList<BoardDTO> dtos = dao.list(nPage, category);
		request.setAttribute("list", dtos);
		

	}
	
}
