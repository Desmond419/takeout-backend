package com.desmond.controller;

import com.desmond.common.ResponseResult;
import com.desmond.entity.Cart;
import com.desmond.service.CartService;
import com.desmond.utils.BusinessException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class CartController {
    @Autowired
    private CartService cartService;

    @GetMapping("/carts/{id}")
    public ResponseResult<Cart> getCartById(@PathVariable("id") String id) {
        try {
            return ResponseResult.success("操作成功", cartService.getCartsById(id));
        } catch (BusinessException e) {
            return ResponseResult.fail(e.getMessage());
        }
    }

    @GetMapping("/carts/user/{userId}")
    public ResponseResult<List<Cart>> getCartByUserId(@PathVariable("userId") String userId) {
        try {
            return ResponseResult.success("操作成功", cartService.getCartsByUserId(userId));
        } catch (BusinessException e) {
            return ResponseResult.fail(e.getMessage());
        }
    }

    @PostMapping("/cart")
    public ResponseResult<String> insertCart(@RequestBody Cart cart) {
        try {
            cartService.insertCart(cart);
            return ResponseResult.success();
        } catch (BusinessException e) {
            return ResponseResult.fail(e.getMessage());
        }
    }

    @PutMapping("/cart")
    public ResponseResult<String> updateCart(@RequestBody Cart cart) {
        try {
            cartService.updateCart(cart);
            return ResponseResult.success();
        } catch (BusinessException e) {
            return ResponseResult.fail(e.getMessage());
        }

    }

    @DeleteMapping("/cart")
    public ResponseResult<String> deleteCart(@RequestBody Cart cart) {
        try {
            cartService.deleteCart(cart);
            return ResponseResult.success();
        } catch (BusinessException e) {
            return ResponseResult.fail(e.getMessage());
        }
    }
}

