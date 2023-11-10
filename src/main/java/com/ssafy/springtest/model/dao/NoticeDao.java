package com.ssafy.springtest.model.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.springtest.model.dto.Notice;

@Mapper
public interface NoticeDao {
	int writeNotice(Notice Notice) throws SQLException;

	Notice findNotice(int noticeNo) throws SQLException;

	List<Notice> listNotices() throws SQLException;

	void updateHit(int noticeNo) throws SQLException;

	int modifyNotice(Notice Notice) throws SQLException;

	int deleteNotice(int noticeNo) throws SQLException;
}
