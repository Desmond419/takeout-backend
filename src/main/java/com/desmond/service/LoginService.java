package com.desmond.service;

import com.desmond.common.ResponseResult;
import com.desmond.entity.User;

public interface LoginService {
    ResponseResult<String> login(User user);
    ResponseResult<String> logout();
}
