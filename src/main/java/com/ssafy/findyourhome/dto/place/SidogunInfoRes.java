package com.ssafy.findyourhome.dto.place;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class SidogunInfoRes {

    private String id;
    private String name;
    private int cnt;
    private double lat;
    private double lng;

}
