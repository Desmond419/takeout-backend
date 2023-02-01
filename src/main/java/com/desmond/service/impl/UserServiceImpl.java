package com.desmond.service.impl;

import com.desmond.common.ResponseResult;
import com.desmond.dao.UserDao;
import com.desmond.dao.UserRoleDao;
import com.desmond.entity.User;
import com.desmond.entity.UserRole;
import com.desmond.service.UserService;
import com.desmond.utils.GenUUID;
import io.netty.util.internal.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private UserRoleDao userRoleDao;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public ResponseResult<String> save(User user, String roleId) {
        if(!Objects.isNull(user)){
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
        }
        return new ResponseResult<>(HttpStatus.BAD_REQUEST.value(), "注册失败，请重新注册");
    }
}
