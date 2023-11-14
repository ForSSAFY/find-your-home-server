package com.ssafy.findyourhome.dto;

import com.ssafy.findyourhome.domain.Qna;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class QnaModifyReq {
    private Integer id;
    private String title;
    private String content;

    public Qna toEntity() {
        return Qna.builder()
                .id(id)
                .title(title)
                .content(content)
                .build();
    }
}
