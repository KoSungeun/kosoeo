package com.study.spring.command;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;

import com.study.spring.dao.BDao;
import com.study.spring.dto.BDto;


public class BReplyViewCommand implements BCommand {

	@Autowired
	private ApplicationContext context;
	
	@Override
	public void execute(HttpServletRequest request, Model model){
		String bId = request.getParameter("bId");
		BDao dao = context.getBean("bDao", BDao.class);
		BDto dto = dao.replyView(bId);
		model.addAttribute("reply_view", dto);
	}

}
