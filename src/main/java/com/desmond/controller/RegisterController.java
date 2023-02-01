package com.desmond.controller;

import com.desmond.common.ResponseResult;
import com.desmond.entity.User;
import com.desmond.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class RegisterController {

    @Autowired
    private UserService userService;

    @PostMapping("/user/register")
    public ResponseResult<String> register(@RequestBody User user, @RequestParam(required = false) String roleId){
        return userService.save(user, roleId);
    }
}
