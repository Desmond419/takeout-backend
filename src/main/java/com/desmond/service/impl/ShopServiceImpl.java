package com.desmond.service.impl;

import com.desmond.common.ResponseResult;
import com.desmond.dao.ShopDao;
import com.desmond.entity.Shop;
import com.desmond.service.ShopService;
import com.desmond.utils.BusinessException;
import com.desmond.utils.GenUUID;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class ShopServiceImpl implements ShopService {
    Logger logger = LoggerFactory.getLogger(ShopServiceImpl.class);

    @Autowired
    private ShopDao shopDao;

    @Override
    public List<Shop> getAllShop() {
        try {
            return shopDao.getAllShop();
        } catch (Exception e) {
            logger.error("获取商店异常", e);
            throw new BusinessException("获取失败，请稍后再试");
        }
    }

    @Override
    public List<Shop> getShopByUserId(String userId) {
        if (Objects.isNull(userId)) {
            throw new BusinessException("用户id不得为空");
        }
        try {
            return shopDao.getShopByUserId(userId);
        } catch (Exception e) {
            logger.error("获取商店异常", e);
            throw new BusinessException("获取失败，请稍后再试");
        }
    }

    @Override
    public List<Shop> getShopByCategory(String category) {
        try {
            return shopDao.getShopByCategory(category);
        } catch (Exception e) {
            logger.error("获取商店异常", e);
            throw new BusinessException("获取失败，请稍后再试");
        }
    }

    @Override
    public void addShop(Shop shop) {
        if (Objects.isNull(shop.getUserId())) {
            throw new BusinessException("用户id不得为空");
        }
        try {
            shop.setId(GenUUID.getUUID());
            shopDao.addShop(shop);
        } catch (Exception e) {
            logger.error("创建商店异常", e);
            throw new BusinessException("操作失败，请稍后再试");
        }
    }

    @Override
    public void updateShop(Shop shop) {
        if (Objects.isNull(shop.getUserId()) || Objects.isNull(shop.getId())) {
            throw new BusinessException("id不得为空");
        }
        try {
            shopDao.updateShop(shop);
        } catch (Exception e) {
            logger.error("商店更新异常", e);
            throw new BusinessException("操作失败，请稍后再试");
        }
    }

    @Override
    public void deleteShopById(String shopId) {
        if (Objects.isNull(shopId)) {
            throw new BusinessException("商店id不得为空");
        }
        try {
            shopDao.deleteShopById(shopId);
        } catch (Exception e) {
            logger.error("删除商店异常", e);
            throw new BusinessException("操作失败，请稍后再试");
        }
    }
}
