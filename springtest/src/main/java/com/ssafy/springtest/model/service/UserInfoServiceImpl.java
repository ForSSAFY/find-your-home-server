package com.ssafy.springtest.model.service;

import org.springframework.stereotype.Service;

import com.ssafy.springtest.model.dao.UserInfoDao;
import com.ssafy.springtest.model.dto.UserInfo;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor //파라미터를 해주는 생성자
public class UserInfoServiceImpl implements UserInfoService {
	
	private final UserInfoDao dao;

	@Override
	public UserInfo login(String id, String pw) {
		UserInfo selected = dao.selectById(id);
		if(selected != null && selected.getPw().equals(pw)) {
			return selected;
		}else {
			return null;
		}
	}

}
