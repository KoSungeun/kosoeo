package com.kosoeo.command;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.kosoeo.dao.MemberDAO;
import com.kosoeo.dto.MemberRank;

public class AdminRankCommand implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		MemberDAO dao = new MemberDAO();
		
		
		List<MemberRank> bRank = dao.boardRank();
		List<MemberRank> cRank = dao.commantRank();
		

		request.setAttribute("bRank", new Gson().toJson(bRank));
		request.setAttribute("cRank", new Gson().toJson(cRank));
	}

}
