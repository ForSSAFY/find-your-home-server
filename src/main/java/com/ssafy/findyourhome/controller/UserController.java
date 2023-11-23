package com.ssafy.findyourhome.controller;

import com.ssafy.findyourhome.dto.user.LoginReq;
import com.ssafy.findyourhome.dto.user.LoginRes;
import com.ssafy.findyourhome.dto.user.RegisterReq;
import com.ssafy.findyourhome.service.UserDetailsService;
import com.ssafy.findyourhome.service.UserService;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
@Slf4j
public class UserController {

    private final UserService userService;

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.OK)
    public void register(@RequestBody RegisterReq req) {
        userService.register(req);
    }

}
