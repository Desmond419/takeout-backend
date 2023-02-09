package com.desmond.service.impl;

import com.desmond.common.ResponseResult;
import com.desmond.dao.UserDao;
import com.desmond.dao.UserRoleDao;
import com.desmond.entity.User;
import com.desmond.entity.UserRole;
import com.desmond.service.UserService;
import com.desmond.utils.GenUUID;
import io.netty.util.internal.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class UserServiceImpl implements UserService {
    Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private UserDao userDao;

    @Autowired
    private UserRoleDao userRoleDao;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public ResponseResult<String> save(User user, String roleId) {
        try {
            User username = userDao.findByUsername(user.getUsername());
            if(!Objects.isNull(username)){
                return new ResponseResult<>(HttpStatus.CONFLICT.value(), "用户名已存在，请重试");
            }
            user.setId(GenUUID.getUUID());
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            userDao.save(user);

            if(!StringUtil.isNullOrEmpty(roleId)){
                UserRole userRole = new UserRole();
                userRole.setUserId(user.getId());
                userRole.setRoleId(roleId);
                userRoleDao.save(userRole);
            }
            return new ResponseResult<>(HttpStatus.CREATED.value(), "注册成功");
        } catch (Exception e) {
            logger.error("用户注册异常: " + e.getMessage());
            return new ResponseResult<>(HttpStatus.INTERNAL_SERVER_ERROR.value(), "注册失败，请稍后再试");
        }

    }
}
