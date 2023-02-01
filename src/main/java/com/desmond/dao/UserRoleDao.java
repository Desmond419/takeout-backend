package com.desmond.dao;

import com.desmond.entity.UserRole;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserRoleDao {
    void save(UserRole userRole);
}
