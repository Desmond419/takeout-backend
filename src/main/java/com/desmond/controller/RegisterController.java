package com.desmond.controller;

import com.desmond.common.ResponseResult;
import com.desmond.entity.User;
import com.desmond.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api")
public class RegisterController {

    @Autowired
    private UserService userService;

    @PostMapping("/anonymous/register")
    public ResponseResult<String> register(@RequestBody User user, @RequestParam(required = false) String roleId){
        try {
            userService.save(user, roleId);
            return ResponseResult.success(HttpStatus.CREATED.value(), "注册成功");
        } catch (Exception e) {
            return ResponseResult.fail();
        }
    }
}
