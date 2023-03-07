package com.desmond.entity;

import java.util.Date;

/**
 * 用户实体类
 *
 * @author Desmond
 */
public class User {
    private String id;

    private String username;

    private String password;

    private String nickname;

    private String phone;

    private String gender;

    // 状态 0:禁用，1:正常
    private Integer status;

    // 用户类型 0:管理员，1:普通用户，2:骑手，3:商家
    private String userType;

    private Date createTime;

    private Date updateTime;

    public User() {
    }

    public User(String id, String username, String password, String nickname, String phone, String gender, Integer status, String userType, Date createTime, Date updateTime) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.nickname = nickname;
        this.phone = phone;
        this.gender = gender;
        this.status = status;
        this.userType = userType;
        this.createTime = createTime;
        this.updateTime = updateTime;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}
