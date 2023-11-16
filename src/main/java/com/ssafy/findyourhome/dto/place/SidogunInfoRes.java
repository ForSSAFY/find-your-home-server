package com.ssafy.findyourhome.dto.place;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class SidogunInfoRes {

    private String id;
    private String sidoName;
    private String gugunName;
    private String dongName;
    private int cnt;

}
