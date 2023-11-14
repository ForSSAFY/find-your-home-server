package com.ssafy.springtest.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ssafy.springtest.model.dto.UserInfo;
import com.ssafy.springtest.model.service.UserInfoService;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {
	
	private final UserInfoService service;
	
	@PostMapping("/login")
	public String doLogin(HttpSession session, @RequestParam String id, @RequestParam String pw) {
		UserInfo user = service.login(id, pw);
		if(user!=null) {
			session.setAttribute("loginUser", user);
			return "redirect:/attend/regist";
		}else {
			return "index";
		}
	}
	
	@GetMapping("/logout")
	public String doLogout(HttpSession session) {
		session.invalidate();
		return "redirect:/";
	}
	
}
