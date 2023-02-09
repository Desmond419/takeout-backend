package com.desmond.dao;

import com.desmond.entity.Shop;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ShopDao {
    List<Shop> getAllShop();
    List<Shop> getShopByCategory(String category);
    List<Shop> getShopByUserId(String userId);
    void addShop(Shop shop);
    void updateShop(Shop shop);
    void deleteShopById(String userId);
}
