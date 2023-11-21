package com.ssafy.findyourhome.dto.place;

import lombok.*;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class HouseDetailRes {
    private String id;
    private String name;
    private String address;
    private String date;
    private Double lat;
    private Double lng;
    private int price;
    private String area;
    private List<StoreDto> nearby;
    private List<HouseDealInfoSimpleDto> deals;
}
