package com.ssafy.springtest.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserInfo {
	private String id;
	private String pw;
	private String position;
	private String userNumber;
	private String name;
	private String rname;
	private int rclass;
}
