package com.ssafy.springtest;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.ssafy.springtest.model.dao.MemberDao;
import com.ssafy.springtest.model.dto.MemberDto;

@SpringBootTest
public class MemberDaoTest {
	
	@Autowired
	MemberDao dao;
	
	/*
	 * @Test public void selectTest() { //given String id = "ssafy"; String position
	 * = "관리자";
	 * 
	 * //when MemberDto dto = dao.selectById(id);
	 * 
	 * //then assertEquals(position, dto.getPosition()); }
	 */
	
}

