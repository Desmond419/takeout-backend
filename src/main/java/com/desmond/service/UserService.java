package com.desmond.service;

import com.desmond.common.ResponseResult;
import com.desmond.entity.User;

public interface UserService {
    User findUserById(String id);
    void save(User user, String roleId);
    void deleteUserById(String userId);
    void updateUser(User user);
}
