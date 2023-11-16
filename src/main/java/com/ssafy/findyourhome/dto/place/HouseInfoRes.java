package com.ssafy.findyourhome.dto.place;

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