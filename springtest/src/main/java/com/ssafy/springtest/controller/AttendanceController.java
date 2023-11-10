package com.ssafy.springtest.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ssafy.springtest.model.dto.Attendance;
import com.ssafy.springtest.model.dto.UserInfo;
import com.ssafy.springtest.model.service.AttendanceService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/attend")
@RequiredArgsConstructor
@Slf4j
@Api(value="SSAFY")
public class AttendanceController {
	
	private final AttendanceService service;
	
	@GetMapping("/regist")
	public String goRegist() {
		return "regist";
	}
	
	@PostMapping("/regist")
	public String doRegist(@ModelAttribute Attendance attend) {
		log.debug("Attendance: {}", attend);
		try {
			int result = service.insert(attend);
			return "redirect:/attend/list";
		}catch(Exception e) {
			throw new RuntimeException("문제 내용 - 등록 중 오류가 발생했습니다.");
		}
	}
	
	@GetMapping("/list")
	@ApiOperation(value = "모든 리스트를 반환")
	public String getList(HttpSession session, Model model) {
		UserInfo user = (UserInfo)session.getAttribute("loginUser");
		String userNumber = null;
		if(user!=null && user.getPosition().equals("교육생")) {
			userNumber = user.getUserNumber();
		}
		List<Attendance> list = service.search(userNumber);
		model.addAttribute("list",list);
		return "list";
	}
	
	@GetMapping("/view")
	public String goView(HttpServletRequest request, Model model) {
		String ano = request.getParameter("no");
		log.debug("ano: {}", ano);
		model.addAttribute("detail",service.getAttendanceByAno(ano));
		return "detail";
	}
}
