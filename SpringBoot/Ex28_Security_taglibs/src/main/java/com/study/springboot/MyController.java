package com.study.springboot;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MyController {
	
	
	@RequestMapping("/")
	public @ResponseBody String root() throws Exception{
		return "Security-taglibs (4)";
	}
	
	
	@RequestMapping("/guest/welcome")
	public String welcom1() {
		return "guest/welcome1";
	}

	@RequestMapping("/member/welcome")
	public String welcom2() {
		return "member/welcome2";
	}
	
	@RequestMapping("/admin/welcome")
	public String welcom3() {
		return "admin/welcome3";
	}
	
	@RequestMapping("/loginForm")
	public String loginForm() {
		return "security/loginForm";
	}
	
	@RequestMapping("/loginError")
	public String loginError() {
		return "security/loginError";
	}


}
