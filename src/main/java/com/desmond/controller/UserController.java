package com.desmond.controller;

import com.desmond.common.ResponseResult;
import com.desmond.entity.User;
import com.desmond.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    private UserService userService;

    @PutMapping("/user")
    public ResponseResult<String> updateUserById(@RequestBody User user) {
        try {
            userService.updateUser(user);
            return new ResponseResult<>(HttpStatus.OK.value(), "操作成功");
        } catch (Exception e) {
            return new ResponseResult<>(HttpStatus.INTERNAL_SERVER_ERROR.value(), "服务器异常，请稍后再试");
        }
    }

    /**
     * 注销账户
     *
     * @param userId 用户id
     */
    @PutMapping("/user/{userId}")
    public ResponseResult<String> deleteUserById(@PathVariable("userId") String userId) {
        try {
            userService.deleteUserById(userId);
            return new ResponseResult<>(HttpStatus.OK.value(), "账户注销成功");
        } catch (Exception e) {
            return new ResponseResult<>(HttpStatus.INTERNAL_SERVER_ERROR.value(), "服务器异常，请稍后再试");
        }
    }
}
