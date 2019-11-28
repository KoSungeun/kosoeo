package com.wooa.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class FrontController {


	@RequestMapping("/")
	public String root() throws Exception{
		return "main";
	}

	@RequestMapping("/Board/notice")
	public String boardNotice (Model model) {
		
		return "Board/notice";
	}
	@RequestMapping("/Member/login")
	public String memberLogin (Model model) {


		return "Member/login";
	}
	

}
