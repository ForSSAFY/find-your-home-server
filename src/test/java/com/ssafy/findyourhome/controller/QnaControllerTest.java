package com.ssafy.findyourhome.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ssafy.findyourhome.dao.QnaDao;
import com.ssafy.findyourhome.domain.Qna;
import com.ssafy.findyourhome.dto.QnaWriteReq;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class QnaControllerTest {

    @Autowired
    protected MockMvc mockMvc;

    @Autowired
    protected ObjectMapper objectMapper;

    @Autowired
    private WebApplicationContext context;

    @Autowired
    QnaDao qnaDao;

    @BeforeEach
    public void setMockMvc() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
        qnaDao.deleteAll();
    }

    @DisplayName("write")
    @Test
    public void write() throws Exception {
        // given
        final String url = "/api/qna";
        final Integer userId = 1;
        final String title = "title";
        final String content = "content";
        final QnaWriteReq req = new QnaWriteReq(userId, title, content);
        final String requestBody = objectMapper.writeValueAsString(req);
        List<Qna> qnaListBefore = qnaDao.findAll();
        int size = qnaListBefore.size();

        // when
        ResultActions result = mockMvc.perform(post(url)
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(requestBody));

        // then
        result.andExpect(status().isCreated());
        List<Qna> qnaList = qnaDao.findAll();
        assertThat(qnaList.size()).isEqualTo(size + 1);
        assertThat(qnaList.get(0).getUserId()).isEqualTo(userId);
        assertThat(qnaList.get(0).getTitle()).isEqualTo(title);
        assertThat(qnaList.get(0).getContent()).isEqualTo(content);
    }
}