package com.ssafy.findyourhome.security;

import com.ssafy.findyourhome.domain.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;

import java.util.ArrayList;

@Getter
@Setter
public class AccountContext extends org.springframework.security.core.userdetails.User {
    private User account;

    public AccountContext(User account, ArrayList<GrantedAuthority> roles) {
        super(account.getUsername(), account.getPassword(), roles);
        this.account = account;
    }
}

