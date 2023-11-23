package com.ssafy.findyourhome.service;

import com.ssafy.findyourhome.dao.QnaDao;
import com.ssafy.findyourhome.dao.UserDao;
import com.ssafy.findyourhome.domain.Qna;
import com.ssafy.findyourhome.dto.qna.QnaModifyReq;
import com.ssafy.findyourhome.dto.qna.QnaWriteReq;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class QnaService {

    private final QnaDao qnaDao;
    private final UserDao userDao;

    public void write(QnaWriteReq dto) {
        Qna qna = Qna.builder()
                .userId(1)
                .title(dto.getTitle())
                .content(dto.getContent())
                .build();
        qnaDao.insert(qna);
    }

    public List<Qna> list() {
        return qnaDao.findAll();
    }

    public void increaseViews(int id) {
        qnaDao.increaseViews(id);
    }

    public Qna getQna(int id) {
        return qnaDao.findById(id);
    }

    public void modify(QnaModifyReq dto) {
        qnaDao.update(dto.toEntity());
    }

    public void delete(int id) {
        qnaDao.delete(id);
    }
}
