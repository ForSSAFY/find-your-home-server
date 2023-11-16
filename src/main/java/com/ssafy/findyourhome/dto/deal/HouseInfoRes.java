package com.ssafy.findyourhome.dto.deal;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class HouseInfoRes {

    private String id;
    private Double lat;
    private Double lng;
    private String name;
}