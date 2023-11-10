package com.ssafy.springtest.model.service;

import java.sql.SQLException;
import java.util.List;

import com.ssafy.springtest.model.dto.Notice;

public interface NoticeService {
	List<Notice> list() throws SQLException;

	Notice view(int noticeNo) throws SQLException;

	int write(String userId, String subject, String content) throws SQLException;

	int modify(int noticeNo, String subject, String content, String sessionUserId) throws SQLException;

	int delete(int noticeNo, String sessionUserId) throws SQLException;
	
	void updateHit(int noticeNo) throws SQLException;
}
