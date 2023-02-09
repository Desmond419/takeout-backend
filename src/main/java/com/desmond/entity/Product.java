package com.desmond.entity;

import java.util.Date;

/**
 * 商品（食物）实体类
 *
 * @author Desmond
 */
public class Product {
    private String id;

    private String shopId;

    // 食物名字
    private String name;

    // 食物价格
    private String price;

    // 食材描述
    private String description;

    // 食物照片
    private String photo;

    private Date createTime;

    private Date updateTime;

    public Product() {
    }

    public Product(String id, String shopId, String name, String price, String description, String photo, Date createTime, Date updateTime) {
        this.id = id;
        this.shopId = shopId;
        this.name = name;
        this.price = price;
        this.description = description;
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

    public String getShopId() {
        return shopId;
    }

    public void setShopId(String shopId) {
        this.shopId = shopId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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
