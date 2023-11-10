package com.ssafy.springtest.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class MainController {
	
	@GetMapping({ "/", "/index" })
	public String index() {
		return "index"; // logical한 view 이름
	}
	
}
