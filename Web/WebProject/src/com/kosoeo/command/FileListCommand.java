package com.kosoeo.command;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import com.kosoeo.dao.FileDAO;

import com.kosoeo.dto.File;

public class FileListCommand implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		

		int boardNo = 0;
		try {
			boardNo = Integer.parseInt(request.getParameter("no"));
		} catch (NumberFormatException e) {
			
		}
		
		List<File> files = null;
		FileDAO fdao = new FileDAO();
		files = fdao.list(boardNo);


		request.setAttribute("files", files);
	}

}
