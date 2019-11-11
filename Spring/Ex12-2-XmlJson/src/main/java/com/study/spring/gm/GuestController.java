package com.study.spring.gm;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class GuestController {
	@RequestMapping(value = "/list.xml")
	@ResponseBody
	public GuestMessageXMLList listXml() {
		return getMessageXMLList();
	}
	
	@RequestMapping(value = "/list.json")
	@ResponseBody
	public GuestMessageJSONList listJson() {
		return getMessageJsonList();
	}
	
	private GuestMessageXMLList getMessageXMLList() {
		List<GuestMessage> messages = Arrays.asList(
				new GuestMessage(1,"홍길동",new Date()),
				new GuestMessage(2,"전우치",new Date())
				);
		
		return new GuestMessageXMLList(messages);
	}
	

	public GuestMessageJSONList getMessageJsonList() {
		List<GuestMessage> messages = Arrays.asList(
				new GuestMessage(1,"홍길동",new Date()),
				new GuestMessage(2,"전우치",new Date())
				);
		return new GuestMessageJSONList(messages);
	}
}
