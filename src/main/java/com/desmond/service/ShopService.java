package com.desmond.service;

import com.desmond.common.ResponseResult;
import com.desmond.entity.Shop;

import java.util.List;

public interface ShopService {
    List<Shop> getAllShop();
    List<Shop> getShopByCategory(String category);
    List<Shop> getShopByUserId(String userId);
    void addShop(Shop shop);
    void updateShop(Shop shop);
    void deleteShopById(String userId);
}
