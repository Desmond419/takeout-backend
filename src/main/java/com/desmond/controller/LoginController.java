package com.desmond.controller;

import com.desmond.common.ResponseResult;
import com.desmond.entity.User;
import com.desmond.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class LoginController {

    @Autowired
    private LoginService loginService;

    @PostMapping("/auth/login")
    public ResponseResult<String> login(@RequestBody User user){
        return loginService.login(user);
    }

    @PostMapping("/logout")
    public ResponseResult<String> logout(){
        return loginService.logout();
    }
}