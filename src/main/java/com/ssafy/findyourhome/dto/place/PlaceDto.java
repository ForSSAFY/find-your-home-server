package com.ssafy.findyourhome.dto.place;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class PlaceDto {

    private String id;
    private String type;
    private String name;
    private String address;
    private double lat;
    private double lng;
}
