package com.kosoeo.command;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kosoeo.dao.BoardDAO;

public class MainPageCommand implements Command{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		BoardDAO dao = new BoardDAO();
		
		request.setAttribute("hlist", dao.mostHitList());
		request.setAttribute("tlist", dao.mostThumbUpList());
		request.setAttribute("nlist", dao.miniNotice());
	}

}
