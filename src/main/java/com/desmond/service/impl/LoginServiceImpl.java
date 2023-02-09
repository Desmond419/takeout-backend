package com.desmond.service.impl;

import com.desmond.common.ResponseResult;
import com.desmond.entity.LoginUser;
import com.desmond.entity.User;
import com.desmond.jwt.JwtUtil;
import com.desmond.service.LoginService;
import com.desmond.utils.RedisCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Objects;

@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private RedisCache redisCache;

    @Override
    public ResponseResult<String> login(User user) {
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword());
        Authentication authenticate = authenticationManager.authenticate(authenticationToken);
        if (Objects.isNull(authenticate)) {
            throw new RuntimeException("用户名或密码错误");
        }
        LoginUser loginUser = (LoginUser) authenticate.getPrincipal();
        String userId = loginUser.getUser().getId();
        String jwt = JwtUtil.createJWT(userId);

        // 将已登入的用户信息存入redis
        redisCache.setCacheObject("LoginUser: " + userId, loginUser);
        HashMap<String, String> map = new HashMap<>();
        map.put("userId", userId);
        map.put("userType", loginUser.getUser().getUserType());
        map.put("token", jwt);
        return new ResponseResult(HttpStatus.OK.value(), "登录成功", map);
    }

    @Override
    public ResponseResult<String> logout() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        LoginUser loginUser = (LoginUser) authentication.getPrincipal();
        String userid = loginUser.getUser().getId();

        // 将对应的用户id从redis中删除
        redisCache.deleteObject("LoginUser: "+userid);
        return new ResponseResult<>(HttpStatus.OK.value(), "退出成功");
    }
}