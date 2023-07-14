package com.desmond.controller;

import com.desmond.common.ResponseResult;
import com.desmond.entity.User;
import com.desmond.service.UserService;
import com.desmond.utils.BusinessException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;

@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    private UserService userService;

    @Value("${file.upload-dir}")
    private String uploadDir;

    @PostMapping("/user/upload-avatar")
    public ResponseResult<String> uploadFile(@RequestParam("userId") String userId, @RequestParam("file") MultipartFile file) {
        try {
            String fileType = Objects.requireNonNull(file.getOriginalFilename()).split("\\.")[1];
            if (!(fileType.equals("jpg") || fileType.equals("jpeg"))) {
                return ResponseResult.fail("图片仅支持jpg/jpeg格式");
            }
            String newFileName = "avatar_" + userId + ".jpg";
            Path newPath = Paths.get(uploadDir + newFileName);
            // 删除旧头像
            String oldFileName = "avatar_" + userId + ".jpg";
            Path oldPath = Paths.get(uploadDir + oldFileName);
            if (Files.exists(oldPath)) {
                Files.delete(oldPath);
            }
            // 保存新头像到服务器
            Files.write(newPath, file.getBytes());
            // 返回文件URL地址
            String fileUrl = "/static/" + newFileName;
            return ResponseResult.success("头像上传成功", fileUrl);
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseResult.fail();
        }
    }

    @GetMapping("/user/avatar/{userId}")
    public ResponseResult<String> getAvatar(@PathVariable("userId") String userId) {
        String fileName = "avatar_" + userId + ".jpg";
        // 构造文件路径
        Path path = Paths.get(uploadDir + fileName);
        if (!Files.exists(path)) {
            return ResponseResult.fail("头像文件不存在");
        }
        String fileUrl = "/static/" + fileName;
        return ResponseResult.success(fileUrl);
    }

    @GetMapping("/user/{id}")
    public ResponseResult<User> getUserById(@PathVariable("id") String id) {
        try {
            return ResponseResult.success("操作成功", userService.findUserById(id));
        } catch (BusinessException e) {
            return ResponseResult.fail(e.getMessage());
        }
    }

    @PutMapping("/user")
    public ResponseResult<String> updateUserById(@RequestBody User user) {
        try {
            userService.updateUser(user);
            return ResponseResult.success();
        } catch (BusinessException e) {
            return ResponseResult.fail(e.getMessage());
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
            return ResponseResult.success("账户注销成功");
        } catch (BusinessException e) {
            return ResponseResult.fail(e.getMessage());
        }
    }
}
