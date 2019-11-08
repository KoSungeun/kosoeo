package com.study.spring;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
		
		model.addAttribute("serverTime", formattedDate );
		
		return "home";
	}
	
	@RequestMapping("/guest/welcome")
	public String welcome1(Locale locale, Model model) {
		return "guest/welcome1";
	}
	
	@RequestMapping("/member/welcome")
	public String welcome2(Locale locale, Model model) {
		return "member/welcome2";
	}
	
	@RequestMapping("/admin/welcome")
	public String welcome3(Locale locale, Model model) {
		return "admin/welcome3";
	}
	
	@RequestMapping("/loginForm")
	public String loginForm(Locale locale, Model model) {
		return "security/loginForm";
	}
	
	@RequestMapping("/loginError")
	public String loginError(Locale locale, Model model) {
		return "security/loginError";
	}
	
}
