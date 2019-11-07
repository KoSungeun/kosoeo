package com.study.spring.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.study.spring.command.BCommand;
import com.study.spring.command.BContentCommand;
import com.study.spring.command.BDeleteCommand;
import com.study.spring.command.BListCommand;
import com.study.spring.command.BModifyCommnad;
import com.study.spring.command.BReplyCommand;
import com.study.spring.command.BReplyViewCommand;
import com.study.spring.command.BWriteCommand;

@Controller
public class BController {
	
	@Autowired
	private ApplicationContext context;
	
	BCommand command = null;
	
	@RequestMapping("/write_view")
	public String writeView(HttpServletRequest request, Model model) {
		return "write_view";
	}
	@RequestMapping("/write")
	public String write(HttpServletRequest request, Model model) {
		command = (BWriteCommand) context.getBean("writeHandler");
		command.execute(request, model);
		return "redirect:list";
	}
	@RequestMapping("/list")
	public String list(HttpServletRequest request, Model model) {
		command = context.getBean(BListCommand.class);
		command.execute(request, model);
		return "list";
	}
	@RequestMapping("/content_view")
	public String contentView(HttpServletRequest request, Model model) {
		command = (BContentCommand) context.getBean("contentHandler");
		command.execute(request, model);
		return "content_view";
	}
	@RequestMapping("/modify_view")
	public String modifyView(HttpServletRequest request, Model model) {
		command = (BContentCommand) context.getBean("contentHandler");
		command.execute(request, model);
		return "modify_view";
	}
	@RequestMapping("/modify")
	public String modify(HttpServletRequest request, Model model) {
		command = (BModifyCommnad) context.getBean("modifyHandler");
		command.execute(request, model);
		command = (BContentCommand) context.getBean("contentHandler");
		command.execute(request, model);
		return "content_view";
	}
	@RequestMapping("/delete")
	public String delete(HttpServletRequest request, Model model) {
		command = (BDeleteCommand) context.getBean("deleteHandler"); 
		command.execute(request, model);
		return "redirect:list?page=" +request.getSession().getAttribute("cpage");
	}
	@RequestMapping("/reply_view")
	public String replyView(HttpServletRequest request, Model model) {
		command = (BReplyViewCommand) context.getBean("replyViewHandler");
		command.execute(request, model);
		return "reply_view";
	}
	@RequestMapping("/reply")
	public String reply(HttpServletRequest request, Model model) {
		command = (BReplyCommand) context.getBean("replyHandler");
		command.execute(request, model);
		return "redirect:list?page=" +request.getSession().getAttribute("cpage");
	}
	
	
}
