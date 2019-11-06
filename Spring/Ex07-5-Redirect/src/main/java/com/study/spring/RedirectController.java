package com.study.spring;


import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Handles requests for the application home page.
 */
@Controller
public class RedirectController {
	
	@RequestMapping("/studentConfirm")
	public String studentRedirect(HttpServletRequest httpServletRequest, Model model) {
		
		String id = httpServletRequest.getParameter("id");
		if(id.equals("abc")) {
			return "redirect:studentOk";
		}
		
		return "redirect:studentNg";
	}
	
	
	@RequestMapping("/studentOk")
	public String sutdentOk(Model model) {
		
		return "student/studentOk";
	}
	
	@RequestMapping("/studentNg")
	public String sutdentNg(Model model) {
		
		return "student/studentNg";
	}
}
