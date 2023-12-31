package com.ssafy.findyourhome.dto.place;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class HouseDealInfoSimpleDto {

    private String date;
    private int price;
    private int floor;
}
