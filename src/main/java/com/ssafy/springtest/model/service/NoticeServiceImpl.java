package com.ssafy.springtest.model.service;

import java.sql.SQLException;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssafy.springtest.model.dao.NoticeDao;
import com.ssafy.springtest.model.dto.Notice;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class NoticeServiceImpl implements NoticeService {

	private final NoticeDao dao;
	
	@Override
	@Transactional(readOnly = true)
	public List<Notice> list() throws SQLException {
		log.debug("NoticeService list: {}",dao.listNotices());
		return dao.listNotices();
	}

	@Override
	public Notice view(int noticeNo) throws SQLException {
		return dao.findNotice(noticeNo);
	}

	@Override
	public int write(String userId, String subject, String content) throws SQLException {
		Notice notice = new Notice();
		notice.setUserId(userId);
		notice.setSubject(subject);
		notice.setContent(content);
		return dao.writeNotice(notice);
	}

	@Override
	public int modify(int noticeNo, String subject, String content, String sessionUserId) throws SQLException {
		Notice notice = dao.findNotice(noticeNo);
		if(notice == null) return 0;
		if(!notice.getUserId().equals(sessionUserId)) return 0;
		notice.setSubject(subject);
		notice.setContent(content);
		return dao.modifyNotice(notice);
	}

	@Override
	public int delete(int noticeNo, String sessionUserId) throws SQLException {
		Notice notice = dao.findNotice(noticeNo);
		if(notice == null) return 0;
		if(!notice.getUserId().equals(sessionUserId)) return 0;
		dao.deleteNotice(noticeNo);
		return 1;
	}

	@Override
	public void updateHit(int noticeNo) throws SQLException {
		dao.updateHit(noticeNo);
	}

}
