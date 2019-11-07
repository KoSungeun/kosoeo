package com.study.spring.command;

import javax.servlet.http.HttpServletRequest;


import org.springframework.ui.Model;

import com.study.spring.dao.BDao;
import com.study.spring.dto.BDto;

public class BReplyCommand implements BCommand {

	@Override
	public void execute(HttpServletRequest request, Model model) {
		String bName = request.getParameter("bName");
		String bTitle = request.getParameter("bTitle");
		String bContent = request.getParameter("bContent");
		
		
		int bGroup = Integer.parseInt(request.getParameter("bGroup"));
		int bStep = Integer.parseInt(request.getParameter("bStep"));
		int bIndent = Integer.parseInt(request.getParameter("bIndent"));
		
		BDto dto = new BDto();
		dto.setbName(bName);
		dto.setbTitle(bTitle);
		dto.setbContent(bContent);
		dto.setbGroup(bGroup);
		dto.setbStep(bStep);
		dto.setbIndent(bIndent);
		
		BDao dao = new BDao();
		dao.reply(dto);
	}

}
