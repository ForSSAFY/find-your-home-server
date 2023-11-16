package com.ssafy.findyourhome.dto.place;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class HouseDealInfoSimpleDto {
    private String dongCode;
    private int dealYear;
    private int dealMonth;
}
