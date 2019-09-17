package com.study.jsp.command;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.jasper.runtime.JspRuntimeLibrary;

import com.study.jsp.dao.BDao;
import com.study.jsp.dto.BDto;

public class BReplyCommand implements BCommand {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		BDto dto = new BDto();
		JspRuntimeLibrary.introspect(dto, request);
		BDao dao = new BDao();
		dao.reply(dto);
	}

}
