package com.study.spring.command;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;

import com.study.spring.dao.BDao;
import com.study.spring.dto.BDto;

public class BReplyViewCommand implements BCommand {

	@Override
	public void execute(HttpServletRequest request, Model model){
		String bId = request.getParameter("bId");
		BDao dao = new BDao();
		BDto dto = dao.replyView(bId);
		model.addAttribute("reply_view", dto);
	}

}
