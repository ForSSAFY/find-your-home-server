package com.ssafy.findyourhome.controller;

import com.ssafy.findyourhome.domain.Qna;
import com.ssafy.findyourhome.dto.qna.QnaModifyReq;
import com.ssafy.findyourhome.dto.qna.QnaWriteReq;
import com.ssafy.findyourhome.service.QnaService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.nio.charset.StandardCharsets;
import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/notice")
@RequiredArgsConstructor
@Slf4j
public class NoticeController {

    private final QnaService qnaService;
    private final String NAME = "[QnaController]";

    @PostMapping
    public ResponseEntity<?> write(@RequestBody QnaWriteReq dto) {
        log.info("{} write {}", NAME, dto);
        try {
            qnaService.write(dto);
            return new ResponseEntity<Void>(HttpStatus.CREATED);
        } catch (Exception e) {
            return exceptionHandling(e);
        }
    }

    @GetMapping
    public ResponseEntity<?> list() {
        log.info("{} list", NAME);
        try {
            List<Qna> qnaList = qnaService.list();
            HttpHeaders header = new HttpHeaders();
            header.setContentType(new MediaType("application", "json", StandardCharsets.UTF_8));
            return ResponseEntity.ok().headers(header).body(qnaList);
        } catch (Exception e) {
            return exceptionHandling(e);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Qna> view(@PathVariable("id") int id) {
        log.info("{} view {}", NAME, id);
        qnaService.increaseViews(id);
        return new ResponseEntity<>(qnaService.getQna(id), HttpStatus.OK);
    }

    @GetMapping("/modify/{id}")
    public ResponseEntity<Qna> modifyForm(@PathVariable("id") int id) {
        log.info("{} modifyForm {}", NAME, id);
        return new ResponseEntity<>(qnaService.getQna(id), HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<String> modify(@RequestBody QnaModifyReq dto) {
        log.info("{} modify {}", NAME, dto);
        qnaService.modify(dto);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable("id") int id) {
        log.info("{} delete", NAME);
        qnaService.delete(id);
        return ResponseEntity.ok().build();
    }

    private ResponseEntity<String> exceptionHandling(Exception e) {
        e.printStackTrace();
        return new ResponseEntity<String>("Error : " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
