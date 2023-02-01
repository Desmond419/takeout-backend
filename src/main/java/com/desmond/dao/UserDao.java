package com.desmond.dao;

import com.desmond.entity.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserDao {
    User findByUsername(String username);
    void save(User user);
}