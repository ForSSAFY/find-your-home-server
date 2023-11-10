package com.ssafy.springtest.api.controller;

import javax.servlet.http.HttpSession;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.springtest.model.dto.MemberDto;
import com.ssafy.springtest.model.service.MemberService;

import lombok.extern.slf4j.Slf4j;


@RestController
@RequestMapping("/api/user")
@Slf4j
public class MemberApiController {

    private final MemberService memberService;

    public MemberApiController(MemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping("/{userId}")
    public ResponseEntity<MemberDto> getUserInfo(@PathVariable String userId) throws Exception {
        MemberDto user = memberService.getMemberInfo(userId);
        if (user != null) {
            return ResponseEntity.ok(user);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestParam String userId, @RequestParam String pw, HttpSession session) throws Exception {
        log.debug("userId: {}, pw: {}", userId, pw);
    	byte[] pwByte = pw.getBytes();
        MemberDto user = memberService.login(userId, pwByte);
        if (user == null) {
            return ResponseEntity.status(401).body("Invalid credentials");
        } else {
            session.setAttribute("user", user);
            return ResponseEntity.ok("Login successful");
        }
    }

    @PostMapping("/logout")
    public ResponseEntity<String> logout(HttpSession session) {
        session.invalidate();
        return ResponseEntity.ok("Logout successful");
    }

    @PutMapping("/{userId}")
    public ResponseEntity<String> modifyUser(@PathVariable String userId, @RequestParam String username,
                                             @RequestParam String address, @RequestParam String phone, HttpSession session) throws Exception {
        MemberDto currentUser = (MemberDto) session.getAttribute("user");
        if (currentUser.getUserId().equals(userId)) {
            MemberDto memberDto = memberService.getMemberInfo(userId);
            memberDto.setUserName(username);
            memberDto.setAddress(address);
            memberDto.setPhone(phone);
            memberService.modifyMember(memberDto);
            memberDto = memberService.getMemberInfo(userId);
            session.setAttribute("user", memberDto);
            return ResponseEntity.ok("User modified successfully");
        } else {
            return ResponseEntity.status(403).body("Access forbidden");
        }
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<String> deleteUser(@PathVariable String userId, HttpSession session) throws Exception {
        MemberDto currentUser = (MemberDto) session.getAttribute("user");
        // auto logout when delete account
        session.removeAttribute("user");
        if (currentUser.getUserId().equals(userId)) {
            memberService.delete(userId);
            return ResponseEntity.ok("User deleted successfully");
        } else {
            return ResponseEntity.status(403).body("Access forbidden");
        }
    }

    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestParam String userId, @RequestParam String username,
                                              @RequestParam String pw, @RequestParam String address, @RequestParam String phone) throws Exception {
    	log.debug("userId: {}, pw: {}", userId, pw);
        byte[] pwByte = pw.getBytes();
        String salt = memberService.getSalt();
        MemberDto memberDto = new MemberDto().builder()
                .userId(userId)
                .userName(username)
                .userPassword(memberService.Hashing(pwByte, salt))
                .address(address)
                .phone(phone)
                .salt(salt)
                .build();
        memberService.register(memberDto);
        return ResponseEntity.ok("User registered successfully");
    }

    @GetMapping("/find-pw")
    public ResponseEntity<String> getFindPw() {
        return ResponseEntity.ok("Find Password Page");
    }

    @PostMapping("/find-pw")
    public ResponseEntity<String> postFindPw(@RequestParam String userid, @RequestParam String username,
                                             @RequestParam String phone, @RequestParam String address) throws Exception {
        String userPw = memberService.findUserPw(userid, username, phone);
        return ResponseEntity.ok("Found Password: " + userPw);
    }

    @GetMapping("/change-pw")
    public ResponseEntity<String> getChangePw() {
        return ResponseEntity.ok("Change Password Page");
    }

    @PostMapping("/change-pw")
    public ResponseEntity<String> postChangePw(HttpSession session, @RequestParam String pwcurrent,
                                               @RequestParam String pw, @RequestParam String pwrepeat) throws Exception {
        byte[] pwCurrent = pwcurrent.getBytes();
        if (pw != null && pw.equals(pwrepeat)) {
            MemberDto memberDto = (MemberDto) session.getAttribute("user");
            boolean result = memberService.changeUserPw(memberDto.getUserId(), pwCurrent, pw.getBytes());
            if (result) {
                session.setAttribute("changedPw", true);
            }
            memberDto = memberService.getMemberInfo(memberDto.getUserId());
            session.setAttribute("user", memberDto);
        }
        return ResponseEntity.ok("Changed Password");
    }

}
