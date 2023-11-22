package com.ssafy.findyourhome.service;

import com.ssafy.findyourhome.dao.UserDao;
import com.ssafy.findyourhome.domain.User;
import com.ssafy.findyourhome.dto.user.LoginReq;
import com.ssafy.findyourhome.dto.user.LoginRes;
import com.ssafy.findyourhome.dto.user.RegisterReq;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserDao userDao;
    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;

    public void register(RegisterReq registerReq) {
        if (userDao.findByUsername(registerReq.getUsername()) != null) {
            throw new RuntimeException("Username already exists!");
        }

        User user = User.builder()
                .username(registerReq.getUsername())
                .email(registerReq.getEmail())
                .nickname(registerReq.getNickname())
                .password(passwordEncoder.encode(registerReq.getPassword()))
                .build();
        userDao.insert(user);
    }

}
