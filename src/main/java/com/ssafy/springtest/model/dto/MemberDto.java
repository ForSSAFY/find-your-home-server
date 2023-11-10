package com.ssafy.springtest.model.dto;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MemberDto {
    private String userId;
    private String userName;
    private String userPassword;
    private String address;
    private String phone;
    private Date joinDate;
    private String salt;
}
