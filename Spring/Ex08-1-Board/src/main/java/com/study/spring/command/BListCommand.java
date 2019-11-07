package com.study.spring.command;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.ui.Model;

import com.study.spring.BPageInfo;
import com.study.spring.dao.BDao;
import com.study.spring.dto.BDto;

public class BListCommand implements BCommand {

	@Override
	public void execute(HttpServletRequest request, Model model) {
		int nPage = 1;
		try {
			String sPage = request.getParameter("page");
			nPage = Integer.parseInt(sPage);
		} catch (Exception e) {
		}
		
		BDao dao = BDao.getInstance();
		BPageInfo pInfo = dao.articlePage(nPage);
		model.addAttribute("page", pInfo);
		
		nPage = pInfo.getCurPage();
		
		HttpSession session = null;
		session = request.getSession();
		session.setAttribute("cpage", nPage);
		
		ArrayList<BDto> dtos = dao.list(nPage);
		model.addAttribute("list", dtos);
	

	}
	
}
