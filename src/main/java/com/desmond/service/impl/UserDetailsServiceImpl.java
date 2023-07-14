package com.desmond.service.impl;

import com.desmond.dao.PermissionDao;
import com.desmond.dao.UserDao;
import com.desmond.entity.LoginUser;
import com.desmond.entity.User;
import com.desmond.utils.BusinessException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private PermissionDao permissionDao;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userDao.findByUsername(username);
        if(Objects.isNull(user)){
            throw new BusinessException("用户名或密码错误");
        }
        // 根据用户id查询权限信息添加到LoginUser中
        List<String> permissionKeyList = permissionDao.getPermissionByUserId(user.getId());
        return new LoginUser(user, permissionKeyList);
    }
}
