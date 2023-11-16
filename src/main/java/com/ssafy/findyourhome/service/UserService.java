package com.ssafy.findyourhome.service;

import com.ssafy.findyourhome.dao.UserDao;
import com.ssafy.findyourhome.domain.User;
import com.ssafy.findyourhome.dto.user.RegisterReq;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserDao userDao;

    private final PasswordEncoder passwordEncoder;

    public void register(RegisterReq registerReq) {
        if (userDao.findByUsername(registerReq.getUsername()) != null) {
            throw new RuntimeException("Username already exists!");
        }

        User user = User.builder()
                .username(registerReq.getUsername())
                .password(passwordEncoder.encode(registerReq.getPassword()))
                .build();
        userDao.insert(user);
    }
}
