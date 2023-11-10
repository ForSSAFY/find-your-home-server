package com.ssafy.springtest.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Attendance {
	private String ano;
	private String userNumber;
	private String issueDate;
	private String issue;
	
	private UserInfo user;
}
