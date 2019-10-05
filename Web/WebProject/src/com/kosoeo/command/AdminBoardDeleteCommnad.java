package com.kosoeo.command;

import java.io.File;
import java.io.IOException;

import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import com.kosoeo.dao.BoardDAO;
import com.kosoeo.dao.FileDAO;

public class AdminBoardDeleteCommnad implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("utf-8");
		
		int boardNo = 0;
		try {
			boardNo = Integer.parseInt(request.getParameter("boardNo"));
		} catch (NumberFormatException e) {}
			
		
		
		FileDAO fileDao = new FileDAO();


		String filePath = request.getSession().getServletContext().getRealPath("File");

		if(boardNo != 0) {
			List<com.kosoeo.dto.File> files = fileDao.list(boardNo);
			files.forEach(e-> {
				File file = new File(filePath, e.getRealName());
				if(file.exists()) {
					file.delete();
				}
			});
		}
		
		BoardDAO dao = new BoardDAO();
		dao.delete(boardNo);
		
		HttpSession session = request.getSession();
		session.setAttribute("msg", "삭제했습니다.");

	}

}
