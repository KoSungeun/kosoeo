package com.study.spring;

import org.json.simple.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/mc/simple")
public class SimpleConverterController {

	@RequestMapping(method = RequestMethod.GET)
	public String simpleForm() {
		return "mc/simple";
	}
	
	@RequestMapping(method=RequestMethod.POST, produces="text/plain;charset=UTF-8")
	@ResponseBody
	public String simple(@RequestBody String body) {
		JSONObject obj = new JSONObject();
		obj.put("name", "홍길동");
		String str = obj.toJSONString();
		return str;
		
		
		//return body;
	}
	
}
