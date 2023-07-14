package com.desmond.service;

import com.desmond.entity.Cart;

import java.util.List;

public interface CartService {
    Cart getCartsById(String id);

    List<Cart> getCartsByUserId(String userId);

    void insertCart(Cart cart);

    void updateCart(Cart cart);

    void deleteCart(Cart cart);
}
