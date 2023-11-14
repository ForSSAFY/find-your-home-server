package com.ssafy.findyourhome.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.sql.Timestamp;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Qna {

    private Integer id;

    private Integer userId;

    private String title;

    private String content;

    private Integer views;

    private Timestamp createdAt;

    public void setTitle(String title) {
        if (title.isEmpty() || title.length() > 100) {
            throw new RuntimeException("title length: " + title.length());
        }
        this.title = title;
    }

    public void setContent(String content) {
        if (content.isEmpty() || content.length() > 2000) {
            throw new RuntimeException("content length: " + content.length());
        }
        this.content = content;
    }

    public void incrementViews() {
        views++;
    }
}
