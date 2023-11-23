package com.ssafy.findyourhome.controller;

import com.ssafy.findyourhome.dto.user.RegisterReq;
import com.ssafy.findyourhome.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
@Slf4j
public class UserController {

    private final UserService userService;
//
//    @GetMapping("/api/user/login")
//    @ResponseStatus(HttpStatus.OK)
//    public void login() {
//
//    }


    @PostMapping("/register")
    @ResponseStatus(HttpStatus.OK)
    public void register(@RequestBody RegisterReq req) {
        userService.register(req);
    }

}
