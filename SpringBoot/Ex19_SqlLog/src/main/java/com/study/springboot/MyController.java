package com.study.springboot;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.study.springboot.jdbc.IMyUserDao;

@Controller
public class MyController {
	
	@Autowired
	IMyUserDao userDao;
	
	@RequestMapping("/")
	public @ResponseBody String root() throws Exception{
		return "MyBatis 사용하기";
	}
	
	
	@RequestMapping("/user")
	public String userlistPage(Model model) {
		model.addAttribute("users", userDao.getUser());
		return "userlist";
	}
	


}
