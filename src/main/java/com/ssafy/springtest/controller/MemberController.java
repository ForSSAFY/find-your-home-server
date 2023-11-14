package com.ssafy.springtest.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ssafy.springtest.model.dto.MemberDto;
import com.ssafy.springtest.model.service.MemberService;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/user")
@RequiredArgsConstructor
public class MemberController {
	
	private final MemberService memberService;
	
	@GetMapping
	public String getInfo(HttpSession session) throws Exception {
        return "user/index";
    }
    
	@GetMapping("/login")
	public String getLogin(HttpSession session) {
		// TODO when signed in already 
		return "user/login";
	}
	
	@PostMapping("/login")
	public String doLogin(HttpSession session, @RequestParam String userid, @RequestParam String pw) throws Exception {
        String userId = userid;
        byte[] pwByte = pw.getBytes();	//패스워드를 byte[]값으로 받아옴.
        MemberDto user = memberService.login(userid, pwByte);
        if (user == null) {
//        	session.setAttribute("msg", "아이디/비밀번호가 잘못되었습니다.");
    		return "user/login";
        } else {
        	session.setAttribute("user", user);
            return "redirect:/";
        }
	}
	
	@GetMapping("/logout")
	public String doLogout(HttpSession session) {
		session.invalidate();
		return "redirect:/";
	}
	
	@GetMapping("/modify")
	public String getModify() {
		return "user/modify";
	}

	@PostMapping("/modify")
    protected String postModify(HttpSession session, @RequestParam String userid, @RequestParam String username, @RequestParam String address, @RequestParam String phone) throws Exception {
        MemberDto currentUser = (MemberDto) session.getAttribute("user");
        if (currentUser.getUserId().equals(userid)) {
            MemberDto memberDto = memberService.getMemberInfo(userid);
            memberDto.setUserName(username);
            memberDto.setAddress(address);
            memberDto.setPhone(phone);
            memberService.modifyMember(memberDto);
            memberDto = memberService.getMemberInfo(memberDto.getUserId());
            session.setAttribute("user", memberDto);
        }
        return "redirect:/user";
    }
    
	@GetMapping("/delete")
	public String getDelete() {
		return "user/delete";
	}
	
	@PostMapping("/delete")
    protected String postDelete(HttpSession session, @RequestParam String userid) throws Exception {
        MemberDto currentUser = (MemberDto) session.getAttribute("user");
        // auto logout when delete account
        session.removeAttribute("user");
        if (currentUser.getUserId().equals(userid)) {
            memberService.delete(userid);
        }
        return "redirect:/";
    }
    
	@GetMapping("/register")
	public String getRegister() {
		return "user/register";
	}
	
	@PostMapping("/register")
	public String postRegister(@RequestParam String userid, @RequestParam String username, @RequestParam String pw, @RequestParam String address, @RequestParam String phone) throws Exception {
        byte[] pwByte = pw.getBytes();
        String salt = memberService.getSalt();
        MemberDto memberDto = new MemberDto().builder()
        		.userId(userid)
        		.userName(username)
        		.userPassword(memberService.Hashing(pwByte, salt))
        		.address(address)
        		.phone(phone)
        		.salt(salt)
        		.build();
        memberService.register(memberDto);
        return "redirect:/user/login";
    }
	
	@GetMapping("/find-pw")
	public String getFindPw() {
		return "user/find-pw";
	}
	
	@PostMapping("/find-pw")
    public String postFindPw(HttpServletRequest request, @RequestParam String userid, @RequestParam String username, @RequestParam String phone, @RequestParam String address) throws Exception {
        String userPw = memberService.findUserPw(userid, username, phone);
        request.setAttribute("foundPw", userPw);
        return "user/found-pw";
    }

    
	@GetMapping("/change-pw")
	public String getChangePw() {
		return "user/change-pw";
	}
	
	@PostMapping("/change-pw")
	public String postChangePw(HttpSession session, HttpServletRequest request, @RequestParam String pwcurrent, @RequestParam String pw, @RequestParam String pwrepeat) throws Exception {
		byte[] pwCurrent = request.getParameter("pwcurrent").getBytes();
		if (pw != null && pw.equals(pwrepeat)) {
            MemberDto memberDto = (MemberDto) session.getAttribute("user");
            boolean result = memberService.changeUserPw(memberDto.getUserId(), pwCurrent, pw.getBytes());
            if (result) {
            	session.setAttribute("changedPw", true);
            }
            memberDto = memberService.getMemberInfo(memberDto.getUserId());
            session.setAttribute("user", memberDto);
        }
        return "user/changed-pw";
    }
	
}
