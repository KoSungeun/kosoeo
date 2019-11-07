package com.study.spring.command;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;

import com.study.spring.dao.BDao;



public class BDeleteCommand implements BCommand {

	@Autowired
	private ApplicationContext context;
	
	@Override
	public void execute(HttpServletRequest request, Model response) {
		String bId = request.getParameter("bId");
		BDao dao = context.getBean("bDao", BDao.class);

		dao.delete(bId);
	}

}
