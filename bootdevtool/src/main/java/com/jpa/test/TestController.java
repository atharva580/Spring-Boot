package com.jpa.test;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class TestController {

	@RequestMapping("/test")
	@ResponseBody
	public String test() {
		int a = 5;
		int b = 7;
		return "This is just testing and sum of a and b is " + (a+b);  
	}
}
