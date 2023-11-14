package com.ssafy.springtest.api.controller;

import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.springtest.model.dto.MemberDto;
import com.ssafy.springtest.model.dto.Notice;
import com.ssafy.springtest.model.service.NoticeService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/notice")
@RequiredArgsConstructor
@Slf4j
public class NoticeApiController {

    private final NoticeService service;

    @GetMapping
    public ResponseEntity<List<Notice>> getList() throws SQLException {
        List<Notice> list = service.list();
        log.debug(">>>list : {}", list);
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("/{noticeNo}")
    public ResponseEntity<Notice> getNotice(@PathVariable int noticeNo) throws SQLException {
        Notice notice = service.view(noticeNo);
        service.updateHit(noticeNo);
        log.debug(">>no : {}", noticeNo);
        return new ResponseEntity<>(notice, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Void> createNotice(@RequestParam String userid, @RequestParam String subject, @RequestParam String content) throws SQLException {
        log.debug("id:{}", userid);
        service.write(userid, subject, content);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/{noticeNo}")
    public ResponseEntity<Void> updateNotice(@PathVariable int noticeNo, @RequestParam String subject, @RequestParam String content, HttpSession session) throws SQLException {
        MemberDto m = (MemberDto) session.getAttribute("user");
        log.debug(">> modify no:{}", noticeNo);
        log.debug(">> modify sessionUserId : {}", m.getUserId());
        service.modify(noticeNo, subject, content, m.getUserId());
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{noticeNo}")
    public ResponseEntity<Void> deleteNotice(@PathVariable int noticeNo, HttpSession session) throws SQLException {
        MemberDto m = (MemberDto) session.getAttribute("user");
        log.debug("sessionUserId : {}", m.getUserId());
        service.delete(noticeNo, m.getUserId());
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
