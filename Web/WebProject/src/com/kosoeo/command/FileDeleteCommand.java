package com.kosoeo.command;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.JsonObject;
import com.kosoeo.dao.FileDAO;


public class FileDeleteCommand implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		response.setContentType("application/json; charset=UTF-8");
		PrintWriter out = response.getWriter();
		
		FileDAO fileDao = new FileDAO();
		int boardNo = 0;
		int fileNo = 0;
		try {
			boardNo = Integer.parseInt(request.getParameter("boardNo"));
			
		} catch (NumberFormatException e) {
			
		}
		try {
			fileNo = Integer.parseInt(request.getParameter("fileNo"));			
		} catch (NumberFormatException e) {
			
		}
		String realName = request.getParameter("realName");	
		String filePath = request.getSession().getServletContext().getRealPath("File");
		
		int result = SUCEESS;
		String msg = "파일삭제 성공";
		
		if(boardNo != 0) {
			List<com.kosoeo.dto.File> files = fileDao.list(boardNo);
			files.forEach(e-> {
				File file = new File(filePath, e.getRealName());
				if(file.exists()) {
					file.delete();
				}
			});
		} else if(fileNo != 0) {
			File file = new File(filePath, realName);
			if(file.exists()) {
				file.delete();
			}
		} else {
			result = PARAM_FAIL;
			msg = "정상적인 접근이 아닙니다";
		}
		
		if(result == SUCEESS) {
			fileDao.delete(boardNo, fileNo);
		}
		if(fileNo != 0) {
			JsonObject json = new JsonObject();
			json.addProperty("result", result);
			json.addProperty("msg", msg);
			out.println(json);
		}
	}

}
