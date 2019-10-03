package com.kosoeo.command;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface Command {
	
	final int LOGIN_FAIL = 0;
	final int CONTENT_FAIL = -1;
	final int BOARD_FAIL = -2;
	final int COMMENT_FAIL = -3;
	final int SESSION_FAIL = -4;
	final int SUCEESS = 1;
	
	void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;
}
