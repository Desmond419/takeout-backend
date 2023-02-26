package com.desmond.controller;

import com.desmond.common.ResponseResult;
import com.desmond.entity.User;
import com.desmond.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Objects;

@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/user/upload-avatar")
    public ResponseResult<String> uploadAvatar(@RequestParam("id") String id, @RequestParam("file") MultipartFile file) {
        User user = userService.findUserById(id);
        if (user == null) {
            return new ResponseResult<>(HttpStatus.BAD_REQUEST.value(), "用户不存在");
        }

        String filename = file.getOriginalFilename();
        String filepath = "uploads/" + filename;
        user.setAvatar(filepath);
        boolean result = userService.updateAvatar(user.getId(), user.getAvatar());

        return result ? new ResponseResult<>(HttpStatus.OK.value(), "头像上传成功")
                : new ResponseResult<>(HttpStatus.INTERNAL_SERVER_ERROR.value(), "头像上传失败");
    }

    @GetMapping("/user/getAvatar/{id}")
    public ResponseEntity<byte[]> getAvatar(@PathVariable("id") String id) {
        byte[] avatarBytes = userService.getAvatarByUserId(id);
        if (avatarBytes == null) {
            return ResponseEntity.notFound().build();
        } else {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.IMAGE_JPEG);
            return new ResponseEntity<>(avatarBytes, headers, HttpStatus.OK);
        }
    }

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
    @DeleteMapping("/user/{userId}")
    public ResponseResult<String> deleteUserById(@PathVariable("userId") String userId) {
        try {
            userService.deleteUserById(userId);
            return new ResponseResult<>(HttpStatus.OK.value(), "账户注销成功");
        } catch (Exception e) {
            return new ResponseResult<>(HttpStatus.INTERNAL_SERVER_ERROR.value(), "服务器异常，请稍后再试");
        }
    }
}
