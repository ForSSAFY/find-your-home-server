package com.ssafy.findyourhome.dto.deal;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class DongCodeDto {
	private String dongCode;
	private String sidoName;
	private String gugunName;
	private String dongName;
}
