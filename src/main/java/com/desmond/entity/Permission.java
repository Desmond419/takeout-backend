package com.desmond.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import java.util.Date;

/**
 * 权限实体类
 *
 * @author Desmond
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Permission {
    private String id;

    // 权限名
    private String name;

    // 权限状态（0正常 1停用）
    private String status;

    // 权限代码
    private String code;

    private String remark;

    private Date createTime;

    private Date updateTime;

    public Permission() {
    }

    public Permission(String id, String name, String status, String code, String remark, Date createTime, Date updateTime) {
        this.id = id;
        this.name = name;
        this.status = status;
        this.code = code;
        this.remark = remark;
        this.createTime = createTime;
        this.updateTime = updateTime;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
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