package com.ssafy.springtest.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

import com.ssafy.springtest.model.dao.UserInfoDao;

@Configuration
@MapperScan(basePackageClasses = UserInfoDao.class)
public class ApplicationConfig {
	
}
