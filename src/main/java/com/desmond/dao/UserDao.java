package com.desmond.dao;

import com.desmond.entity.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserDao {
    User findUserById(String id);
    User findByUsername(String username);
    void save(User user);
    void deleteUserById(String userId);
    void updateUser(User user);
}