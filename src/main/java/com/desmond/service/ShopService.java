package com.desmond.service;

import com.desmond.common.ResponseResult;
import com.desmond.entity.Shop;

import java.util.List;

public interface ShopService {
    ResponseResult<List<Shop>> getAllShop();
    ResponseResult<List<Shop>> getShopByUserId(String userId);
    ResponseResult<List<Shop>> getShopByCategory(String category);
    ResponseResult<String> addShop(Shop shop);
    ResponseResult<String> updateShop(Shop shop);
    ResponseResult<String> deleteShopById(String shopId);
}
