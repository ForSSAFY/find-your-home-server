package com.ssafy.springtest;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.ssafy.springtest.model.dao.UserInfoDao;
import com.ssafy.springtest.model.dto.UserInfo;

@SpringBootTest
public class UserInfoDaoTest {
	
	@Autowired
	UserInfoDao dao;
	
	@Test
	public void selectTest() {
		//given
		String id = "ssafy";
		String position = "관리자";
		
		//when
		UserInfo dto = dao.selectById(id);
		
		//then
		assertEquals(position, dto.getPosition());
	}
	
}

