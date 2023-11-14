package com.ssafy.springtest.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

import com.ssafy.springtest.model.dao.BoardMapper;
import com.ssafy.springtest.model.dao.DongCodeDao;
import com.ssafy.springtest.model.dao.HouseDealDao;
import com.ssafy.springtest.model.dao.MemberDao;
import com.ssafy.springtest.model.dao.NoticeDao;

@Configuration
@MapperScan(basePackageClasses = {MemberDao.class, DongCodeDao.class, HouseDealDao.class, NoticeDao.class, BoardMapper.class})
public class ApplicationConfig {
	
}
