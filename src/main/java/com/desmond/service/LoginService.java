package com.desmond.service;

import com.desmond.common.ResponseResult;
import com.desmond.entity.User;

import java.util.Map;

public interface LoginService {
    ResponseResult<Map<String, String>> login(User user);
    ResponseResult<String> logout();
}
