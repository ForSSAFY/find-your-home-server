package com.ssafy.findyourhome.dto.qna;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.ssafy.findyourhome.domain.Qna;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class QnaWriteReq {

    private Integer userId;
    private String title;
    private String content;

    public Qna toEntity() {
        return Qna.builder()
                .userId(userId)
                .title(title)
                .content(content)
                .build();
    }
}
