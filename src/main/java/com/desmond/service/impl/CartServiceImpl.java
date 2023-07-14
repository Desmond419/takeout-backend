package com.desmond.service.impl;

import com.desmond.dao.CartDao;
import com.desmond.entity.Cart;
import com.desmond.service.CartService;
import com.desmond.utils.BusinessException;
import com.desmond.utils.GenUUID;
import com.desmond.utils.RedisCache;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.TimeUnit;

@Service
public class CartServiceImpl implements CartService {
    Logger logger = LoggerFactory.getLogger(CartServiceImpl.class);

    @Autowired
    private CartDao cartDao;
    @Autowired
    private RedisCache redisCache;

    @Override
    public Cart getCartsById(String id) {
        if (Objects.isNull(id)) {
            throw new BusinessException("id不得为空");
        }
        try {
            return cartDao.getCartsById(id);
        } catch (Exception e) {
            logger.error("购物车异常", e);
            throw new BusinessException("获取失败，请稍后再试");
        }
    }

    @Override
    public List<Cart> getCartsByUserId(String userId) {
        if (Objects.isNull(userId)) {
            throw new BusinessException("用户id不得为空");
        }
        try {
            List<Cart> carts = new ArrayList<>();

            String cacheKey = "UserShoppingCart: " + userId;
            Map<String, Integer> cartItems = redisCache.getCacheMap(cacheKey);
            // 检查购物车数据是否还在Redis里
            if (!cartItems.isEmpty()) {
                for (Map.Entry<String, Integer> entry : cartItems.entrySet()) {
                    String productId = entry.getKey();
                    int quantity = entry.getValue();

                    Cart cart = new Cart();
                    cart.setUserId(userId);
                    cart.setProductId(productId);
                    cart.setQuantity(quantity);
                    carts.add(cart);
                }
            } else {
                // Redis中不存在购物车数据，从数据库中提取
                carts = cartDao.getCartsByUserId(userId);

                // 将购物车数据重新写入Redis
                for (Cart cart : carts) {
                    cartItems.put(cart.getProductId(), cart.getQuantity());
                }
                redisCache.setCacheMap(cacheKey, cartItems);
                redisCache.expire(cacheKey, 30, TimeUnit.MINUTES);
            }
            return carts;
        } catch (Exception e) {
            logger.error("购物车异常", e);
            throw new BusinessException("获取失败，请稍后再试");
        }
    }

    @Override
    public void insertCart(Cart cart) {
        if (Objects.isNull(cart.getUserId()) || Objects.isNull(cart.getProductId())) {
            throw new BusinessException("用户或商品id不得为空");
        }
        try {
            // 同时写入Redis和数据库
            String cacheKey = "UserShoppingCart: " + cart.getUserId();
            redisCache.setCacheMapValue(cacheKey, cart.getProductId(), cart.getQuantity());
            redisCache.expire(cacheKey, 30, TimeUnit.MINUTES);
            cart.setId(GenUUID.getUUID());
            cartDao.insertCart(cart);
        } catch (Exception e) {
            logger.error("添加购物车异常", e);
            throw new BusinessException("操作失败，请稍后再试");
        }
    }

    @Override
    public void updateCart(Cart cart) {
        if (Objects.isNull(cart.getId())) {
            throw new BusinessException("id不得为空");
        }
        try {
            // 同时更新Redis和数据库
            String cacheKey = "UserShoppingCart: " + cart.getUserId();
            redisCache.setCacheMapValue(cacheKey, cart.getProductId(), cart.getQuantity());
            redisCache.expire(cacheKey, 30, TimeUnit.MINUTES);
            cartDao.updateCart(cart);
        } catch (Exception e) {
            logger.error("更新购物车异常", e);
            throw new BusinessException("操作失败，请稍后再试");
        }
    }

    @Override
    public void deleteCart(Cart cart) {
        if (Objects.isNull(cart.getId()) || Objects.isNull(cart.getUserId()) || Objects.isNull(cart.getProductId())) {
            throw new BusinessException("id不得为空");
        }
        try {
            // 同时删除Redis和数据库
            String cacheKey = "UserShoppingCart: " + cart.getUserId();
            redisCache.delCacheMapValue(cacheKey, cart.getProductId());
            cartDao.deleteCartById(cart.getId());
        } catch (Exception e) {
            logger.error("购物车删除异常", e);
            throw new BusinessException("操作失败，请稍后再试");
        }
    }
}
