package com.ssafy.findyourhome.domain;

import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Collection;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User implements Serializable {

    private Integer id;

    private String username;

    private String nickname;
    private String email;
    private String role;
    private String password;

    private Timestamp joinDate;

    public void updatePassword(String password) {
        if (password.isEmpty() || password.length() > 300) {
            throw new RuntimeException("password length: " + password.length());
        }
        this.password = password;
    }
}
