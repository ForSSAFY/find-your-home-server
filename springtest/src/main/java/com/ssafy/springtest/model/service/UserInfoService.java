package com.ssafy.springtest.model.service;

import com.ssafy.springtest.model.dto.UserInfo;

public interface UserInfoService {
	UserInfo login(String id, String pw);
}
