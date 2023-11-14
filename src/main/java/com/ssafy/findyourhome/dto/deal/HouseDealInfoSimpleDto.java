package com.ssafy.findyourhome.dto.deal;

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
