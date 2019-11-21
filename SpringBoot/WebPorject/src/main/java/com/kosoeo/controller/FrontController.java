package com.kosoeo.controller;


import com.kosoeo.dao.BoardDAO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;



@Controller
public class FrontController {

	@Autowired
	BoardDAO boardDao;

	@RequestMapping("/")
	public String root() throws Exception{
		return "main";
	}

	@RequestMapping("/Board/notice")
	public String boardNotice ( Model model) {

		model.addAttribute("list", boardDao.list(1, 1, null, null));
		System.out.println(model.getAttribute("list").toString());
		return "Board/list";
	}
	@RequestMapping("/Member/login")
	public String memberLogin (Model model) {

//		model.addAttribute("list", boardDao.list(1, 1, null, null));
//		System.out.println(model.getAttribute("list").toString());
		return "Member/login";
	}
	

}
