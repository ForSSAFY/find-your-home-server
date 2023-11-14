package com.ssafy.findyourhome.dto.deal;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class HouseDealInfoDto {

    private long no;
    private String dealAmount;
    private int dealYear;
    private int dealMonth;
    private int dealDay;
    private String area;
    private String floor;
    private String cancelDealType;
    private long aptCode;
    private int buildYear;
    private String roadName;
    private String roadNameBonbun;
    private String roadNameBubun;
    private String roadNameSeq;
    private String roadNameBasementCode;
    private String roadNameCode;
    private String dong;
    private String bonbun;
    private String bubun;
    private String sigunguCode;
    private String eubmyundongCode;
    private String dongCode;
    private String landCode;
    private String apartmentName;
    private String jibun;
    private String lng;
    private String lat;

}
