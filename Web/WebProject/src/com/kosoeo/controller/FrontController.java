package com.kosoeo.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kosoeo.command.Command;
import com.kosoeo.command.MemberEmailCheckCommand;
import com.kosoeo.command.MemberJoinCommand;

@WebServlet("*.do")
public class FrontController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		actionDo(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		actionDo(request, response);
	}

	private void actionDo(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		String viewPage = null;
		Command Command = null;

		String uri = request.getRequestURI();
		String conPath = request.getContextPath();
		String com = uri.substring(conPath.length());


		if (com.equals("/Member/joinView.do")) {
			viewPage = "join.jsp";
		} else if (com.equals("/Member/join.do")) {
			Command = new MemberJoinCommand();
			Command.execute(request, response);
			viewPage = "../main.jsp";
		} else if (com.equals("/Member/emailCheck.do")) {
			Command = new MemberEmailCheckCommand();
			Command.execute(request, response);
		} 
		
		if(viewPage != null) {
			RequestDispatcher dispatcher = request.getRequestDispatcher(viewPage);
			dispatcher.forward(request, response);
		}
	}
}
