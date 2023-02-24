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
    public void save(User user, String roleId) {
        try {
            User username = userDao.findByUsername(user.getUsername());
            if(!Objects.isNull(username)){
                throw new RuntimeException("用户名已存在，请重试");
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
        } catch (Exception e) {
            logger.error("用户注册异常", e);
            throw new RuntimeException("注册失败，请稍后再试");
        }
    }

    @Override
    public void deleteUserById(String userId) {
        if (Objects.isNull(userId)) {
            throw new RuntimeException("用户id不得为空");
        }
        try {
            userDao.deleteUserById(userId);
        } catch (Exception e) {
            logger.error("用户注销异常", e);
            throw new RuntimeException("操作失败，请稍后再试");
        }
    }

    @Override
    public void updateUser(User user) {
        if (Objects.isNull(user.getId())) {
            throw new RuntimeException("用户id不得为空");
        }
        try {
            userDao.updateUser(user);
        } catch (Exception e) {
            logger.error("用户更新异常", e);
            throw new RuntimeException("操作失败，请稍后再试");
        }
    }
}
