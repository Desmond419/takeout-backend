package com.desmond.entity;

import java.util.Date;

/**
 * 商店实体类
 *
 * @author Desmond
 */
public class Shop {
    private String id;

    private String userId;

    // 店名
    private String name;

    // 商店评分
    private String rating;

    // 商店月销量
    private String monthlySale;

    // 预计配送时间
    private String estimatedDeliveryTime;

    // 描述
    private String description;

    // 商店种类
    private String category;

    // 商店照片、logo
    private String photo;

    private Date createTime;

    private Date updateTime;

    public Shop() {
    }

    public Shop(String id, String userId, String name, String rating, String monthlySale, String estimatedDeliveryTime, String description, String category, String photo, Date createTime, Date updateTime) {
        this.id = id;
        this.userId = userId;
        this.name = name;
        this.rating = rating;
        this.monthlySale = monthlySale;
        this.estimatedDeliveryTime = estimatedDeliveryTime;
        this.description = description;
        this.category = category;
        this.photo = photo;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getMonthlySale() {
        return monthlySale;
    }

    public void setMonthlySale(String monthlySale) {
        this.monthlySale = monthlySale;
    }

    public String getEstimatedDeliveryTime() {
        return estimatedDeliveryTime;
    }

    public void setEstimatedDeliveryTime(String estimatedDeliveryTime) {
        this.estimatedDeliveryTime = estimatedDeliveryTime;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
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
