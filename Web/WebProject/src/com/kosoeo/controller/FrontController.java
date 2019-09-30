package com.kosoeo.controller;



import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.kosoeo.command.BoardContentCommand;
import com.kosoeo.command.BoardListCommand;
import com.kosoeo.command.BoardWriteCommand;
import com.kosoeo.command.Command;
import com.kosoeo.command.CommentDeleteCommand;
import com.kosoeo.command.CommentListCommand;
import com.kosoeo.command.CommentUpdateCommand;
import com.kosoeo.command.CommentWriteCommand;
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
		
		
		HttpSession session = null;
		session = request.getSession();
		int curCategory = 0;
		String action = "";

		if(session.getAttribute("ccategory") != null ) {
			curCategory = (int) session.getAttribute("ccategory");
			if(curCategory == 0) {
				action = "notice";
			} else if(curCategory == 1) {
				action = "free";
			} else if(curCategory == 2) {
				action = "down";
			}
		}
		
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
			response.sendRedirect(request.getHeader("referer"));
		} else if (com.equals("/Member/modifyView.do")){
			viewPage = "modify.jsp";
		} else if (com.equals("/Member/modify.do")){
			command = new MemberModifyCommand();
			command.execute(request, response);
			response.sendRedirect(request.getParameter("referer"));
		} else if (com.equals("/Member/withdrawView.do")){
			viewPage = "withdraw.jsp";
		} else if (com.equals("/Member/withdraw.do")){
			command = new MemberWithdrawCommand();
			command.execute(request, response);
		} else if (com.equals("/Board/notice.do")){
			command = new BoardListCommand();
			request.setAttribute("category", 0);
			command.execute(request, response);
			viewPage = "/Board/list.jsp";
		} else if (com.equals("/Board/free.do")){
			command = new BoardListCommand();
			request.setAttribute("category", 1);
			command.execute(request, response);
			viewPage = "/Board/list.jsp";
		} else if (com.equals("/Board/down.do")){
			command = new BoardListCommand();
			request.setAttribute("category", 2);
			command.execute(request, response);
			viewPage = "/Board/list.jsp";
		} else if (com.equals("/Board/writeView.do")) {
			request.setAttribute("action", action);
			viewPage = "/Board/write.jsp";
		} else if (com.equals("/Board/write.do")) {
			command = new BoardWriteCommand();
			command.execute(request, response);
			response.sendRedirect("content.do?no="+ request.getAttribute("seq"));
		} else if (com.equals("/Board/content.do")) {
			command = new BoardContentCommand();
			command.execute(request, response);
			viewPage = "content.jsp";
		} else if (com.equals("/Board/commentWrite.do")) {
			command = new CommentWriteCommand();
			command.execute(request, response);
		} else if (com.equals("/Board/commentList.do")) {
			command = new CommentListCommand();
			command.execute(request, response);
		} else if (com.equals("/Board/commentDelete.do")) {
			command = new CommentDeleteCommand();
			command.execute(request, response);
		} else if (com.equals("/Board/commentUpdate.do")) {
			command = new CommentUpdateCommand();
			command.execute(request, response);
		}
		
		
		
		if(viewPage != null) {
			RequestDispatcher dispatcher = request.getRequestDispatcher(viewPage);
			dispatcher.forward(request, response);
		}
	}
}
