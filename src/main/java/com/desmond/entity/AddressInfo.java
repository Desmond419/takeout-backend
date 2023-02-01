package com.desmond.entity;

import java.util.Date;

/**
 * 用户收货地址信息 (AddressInfo)
 *
 * @author Desmond
 */
public class AddressInfo {
    private String id;

    private String userId;

    // 联系人名字
    private String contactName;

    // 联系人性别
    private String contactGender;

    // 联系人手机号
    private String phone;

    // 邮政编码
    private String postalCode;

    // 省份/州
    private String province;

    // 城市名
    private String city;

    // 详细地址: 门牌，路名
    private String details;

    private Date createTime;

    private Date updateTime;

    public AddressInfo() {
    }

    public AddressInfo(String id, String userId, String contactName, String contactGender, String phone, String postalCode, String province, String city, String details, Date createTime, Date updateTime) {
        this.id = id;
        this.userId = userId;
        this.contactName = contactName;
        this.contactGender = contactGender;
        this.phone = phone;
        this.postalCode = postalCode;
        this.province = province;
        this.city = city;
        this.details = details;
        this.createTime = createTime;
        this.updateTime = updateTime;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getContactName() {
        return contactName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    public String getContactGender() {
        return contactGender;
    }

    public void setContactGender(String contactGender) {
        this.contactGender = contactGender;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
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
