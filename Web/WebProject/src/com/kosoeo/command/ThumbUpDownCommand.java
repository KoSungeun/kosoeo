package com.kosoeo.command;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.JsonObject;
import com.kosoeo.dao.CommentDAO;
import com.kosoeo.dao.ThumbDAO;



public class ThumbUpDownCommand implements Command {

	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("application/json; charset=UTF-8");
		PrintWriter out = response.getWriter();
		
		int memberNo = 0;
		int boardNo = 0;
		try {
			memberNo = Integer.parseInt(request.getParameter("memberNo"));
			boardNo = Integer.parseInt(request.getParameter("boardNo"));
			

		} catch (Exception e) {}
		
		

		String upDown = request.getParameter("upDown");
		
		int result = SUCEESS;
		String msg = "";
		
		if(memberNo == 0) {
			result = LOGIN_FAIL;
			msg = "로그인 해주세요.";
		} else if(boardNo == 0 || upDown == null) {
			result = BOARD_FAIL;
			msg = "정상적인 접근이 아닙니다";
		}
			
		if(result == SUCEESS) {
			ThumbDAO dao = new ThumbDAO();
			dao.thumbUpDown(memberNo, boardNo, upDown);
		}
		
		JsonObject json = new JsonObject();
		json.addProperty("result", result);
		json.addProperty("msg", msg);
		out.println(json);
		

	}


}
