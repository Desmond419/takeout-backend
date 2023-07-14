package com.desmond.entity;

import java.util.Date;

/**
 * 购物车实体类
 *
 * @author Desmond
 */
public class Cart {
    private String id;
    private String productId;
    private String userId;
    private Integer quantity;
    private Date createTime;

    public Cart() {
    }

    public Cart(String id, String productId, String userId, Integer quantity, Date createTime) {
        this.id = id;
        this.productId = productId;
        this.userId = userId;
        this.quantity = quantity;
        this.createTime = createTime;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}

