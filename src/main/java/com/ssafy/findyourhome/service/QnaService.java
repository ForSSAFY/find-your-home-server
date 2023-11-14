package com.ssafy.findyourhome.service;

import com.ssafy.findyourhome.dao.QnaDao;
import com.ssafy.findyourhome.domain.Qna;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class QnaService {

    private final QnaDao qnaDao;
    
    public void write(Qna qna) {
        qnaDao.insert(qna);
    }

    public List<Qna> list() {
        return qnaDao.findAll();
    }

    public void increaseViews(int id) {
        qnaDao.increaseViews(qnaDao.findById(id));
    }

    public Qna getQna(int id) {
        return qnaDao.findById(id);
    }

    public void modify(Integer id, Qna qna) {
        qnaDao.update(id, qna);
    }

    public void delete(int id) {
        qnaDao.delete(id);
    }
}
