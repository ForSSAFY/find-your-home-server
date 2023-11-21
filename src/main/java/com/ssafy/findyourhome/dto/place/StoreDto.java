package com.ssafy.findyourhome.dto.place;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class StoreDto {

    private String type; // store : G20405
    private String name;
    private int minutes;
}
