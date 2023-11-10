package com.ssafy.springtest.controller;

import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ssafy.springtest.model.dto.MemberDto;
import com.ssafy.springtest.model.dto.Notice;
import com.ssafy.springtest.model.service.NoticeService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequiredArgsConstructor
@Slf4j
public class NoticeController {
	
	private final NoticeService service;
	
	@GetMapping("/notice")
	public String getList(Model model) throws SQLException {
		List<Notice> list = service.list();
		log.debug(">>>list : {}",list);
		
		model.addAttribute("notices",list);
		return "notice/index";
	}
	
	@GetMapping("/notice/write")
	public String goWrite() {
		return "notice/write";
	}
	
	@PostMapping("/notice/write")
	public String doWrite(@RequestParam String userid, @RequestParam String subject, @RequestParam String content) throws SQLException {
		log.debug("id:{}",userid);
		service.write(userid, subject, content);
		return "redirect:/notice";
	}
	
	@GetMapping("/notice/{noticeNo}")
	public String goView(@PathVariable int noticeNo, Model model) throws SQLException {
		service.updateHit(noticeNo);
		log.debug(">>no : {}",noticeNo);
		model.addAttribute("notice", service.view(noticeNo));
		return "notice/view";
	}
	
	@GetMapping("/notice/{noticeNo}/modify")
	public String goModify(@PathVariable int noticeNo, Model model) throws SQLException {
		log.debug(">>no:{}",noticeNo);
		model.addAttribute("notice", service.view(noticeNo));
		return "notice/modify";
	}
	
	@PostMapping("/notice/{noticeNo}/modify")
	public String doModify(@PathVariable int noticeNo, String subject, String content, HttpSession session) throws SQLException {
		MemberDto m = (MemberDto) session.getAttribute("user");
		log.debug(">> modify no:{}",noticeNo);
		log.debug(">> modify sessionUserId : {}", m.getUserId());
		service.modify(noticeNo, subject, content, m.getUserId());
		return "redirect:/notice";
	}
	
	@PostMapping("/notice/{noticeNo}/delete")
	public String doDelete(@PathVariable int noticeNo, HttpSession session) throws SQLException {
		MemberDto m = (MemberDto) session.getAttribute("user");
		log.debug("sessionUserId : {}", m.getUserId());
		service.delete(noticeNo, m.getUserId());
		return "redirect:/notice";
	}
	
}
