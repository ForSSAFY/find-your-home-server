package com.ssafy.findyourhome.service;

import com.ssafy.findyourhome.dao.NoticeDao;
import com.ssafy.findyourhome.domain.Qna;
import com.ssafy.findyourhome.dto.qna.QnaModifyReq;
import com.ssafy.findyourhome.dto.qna.QnaWriteReq;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class NoticeService {

    private final NoticeDao noticeDao;
    
    public void write(QnaWriteReq dto) {
        noticeDao.insert(dto.toEntity());
    }

    public List<Qna> list() {
        return noticeDao.findAll();
    }

    public void increaseViews(int id) {
        noticeDao.increaseViews(id);
    }

    public Qna getQna(int id) {
        return noticeDao.findById(id);
    }

    public void modify(QnaModifyReq dto) {
        noticeDao.update(dto.toEntity());
    }

    public void delete(int id) {
        noticeDao.delete(id);
    }
}
