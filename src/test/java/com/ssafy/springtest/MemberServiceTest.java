package com.ssafy.springtest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.ssafy.springtest.model.dto.MemberDto;
import com.ssafy.springtest.model.service.MemberService;

@SpringBootTest
public class MemberServiceTest {
	@Autowired
	MemberService service;
	
	/*
	 * @Test public void testLogin() { //given String id = "ssafy"; String pw =
	 * "ssafy";
	 * 
	 * //when MemberDto info = service.login(id, pw);
	 * 
	 * //then assertEquals(info.getPosition(), "관리자");
	 * 
	 * pw = "some"; info = service.login(id, pw); assertNull(info);
	 * 
	 * }
	 */
}
