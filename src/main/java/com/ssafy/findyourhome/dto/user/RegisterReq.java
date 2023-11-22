package com.ssafy.findyourhome.dto.user;

import lombok.Data;

@Data
public class RegisterReq {

    private String username;
    private String nickname;
    private String email;

    private String password;
}
