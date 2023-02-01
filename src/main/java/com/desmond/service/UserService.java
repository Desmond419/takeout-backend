package com.desmond.service;

import com.desmond.common.ResponseResult;
import com.desmond.entity.User;

public interface UserService {
    ResponseResult<String> save(User user, String roleId);
}
