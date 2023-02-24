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

    @PostMapping("/user/register")
    public ResponseResult<String> register(@RequestBody User user, @RequestParam(required = false) String roleId){
        try {
            userService.save(user, roleId);
            return new ResponseResult<>(HttpStatus.CREATED.value(), "注册成功");
        } catch (Exception e) {
            return new ResponseResult<>(HttpStatus.INTERNAL_SERVER_ERROR.value(), "服务器异常，请稍后再试", e.getMessage());
        }
    }
}
