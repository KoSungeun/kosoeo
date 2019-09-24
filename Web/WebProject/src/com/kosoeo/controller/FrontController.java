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
import com.kosoeo.command.MemberLoginCommand;
import com.kosoeo.command.MemberLogoutCommand;
import com.kosoeo.command.MemberModifyCommand;
import com.kosoeo.command.MemberWithdrawCommand;

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
		Command command = null;

		String uri = request.getRequestURI();
		String conPath = request.getContextPath();
		String com = uri.substring(conPath.length());

		if(com.equals("/main.do")) {
			viewPage = "main.jsp";
		} else if (com.equals("/Member/joinView.do")) {
			viewPage = "join.jsp";
		} else if (com.equals("/Member/loginView.do")){
			viewPage = "login.jsp";
		} else if (com.equals("/Member/join.do")) {
			command = new MemberJoinCommand();
			command.execute(request, response);
			viewPage = "../main.jsp";
		} else if (com.equals("/Member/emailCheck.do")) {
			command = new MemberEmailCheckCommand();
			command.execute(request, response);
		} else if (com.equals("/Member/login.do")) {
			command = new MemberLoginCommand();
			command.execute(request, response);
		} else if (com.equals("/Member/logout.do")) {
			command = new MemberLogoutCommand();
			command.execute(request, response);
			response.sendRedirect("../main.do");
		} else if (com.equals("/Member/modifyView.do")){
			viewPage = "modify.jsp";
		} else if (com.equals("/Member/modify.do")){
			command = new MemberModifyCommand();
			command.execute(request, response);
			viewPage = "../main.jsp";
		} else if (com.equals("/Member/withdrawView.do")){
			viewPage = "withdraw.jsp";
		} else if (com.equals("/Member/withdraw.do")){
			command = new MemberWithdrawCommand();
			command.execute(request, response);
		} 
		
		if(viewPage != null) {
			RequestDispatcher dispatcher = request.getRequestDispatcher(viewPage);
			dispatcher.forward(request, response);
		}
	}
}
