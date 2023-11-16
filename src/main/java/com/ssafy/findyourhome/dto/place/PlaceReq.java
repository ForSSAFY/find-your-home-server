package com.ssafy.findyourhome.dto.place;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class PlaceReq {

    private String sido;
    private String gugun;
    private String dong;
    private String dongCode;
    private Integer year;
    private Integer month;
    private Double latNe;
    private Double lngNe;
    private Double latSw;
    private Double lngSw;

    public boolean coordinatesExist() {
        return latNe != null && lngNe != null && latSw != null && lngSw != null;
    }
}
