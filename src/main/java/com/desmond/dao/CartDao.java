package com.desmond.dao;

import com.desmond.entity.Cart;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CartDao {
    Cart getCartsById(String id);

    List<Cart> getCartsByUserId(String userId);

    void insertCart(Cart cart);

    void updateCart(Cart cart);

    void deleteCartById(String id);
}
