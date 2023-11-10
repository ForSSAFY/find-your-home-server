package com.ssafy.springtest.model.dao;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.springtest.model.dto.UserInfo;

@Mapper
public interface UserInfoDao {
	UserInfo selectById(String id);
}
