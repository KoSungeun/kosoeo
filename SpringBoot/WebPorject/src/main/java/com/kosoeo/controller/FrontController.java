package com.kosoeo.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class FrontController {

	@RequestMapping("/")
	public String root() throws Exception{
		return "main";
	}
	

}
