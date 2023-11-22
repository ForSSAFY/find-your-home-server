package com.ssafy.findyourhome.dto.user;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class LoginRes {
    private String username;
    private String nickname;
}
