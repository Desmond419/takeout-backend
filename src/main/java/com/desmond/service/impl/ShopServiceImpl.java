package com.desmond.service.impl;

import com.desmond.common.ResponseResult;
import com.desmond.dao.ShopDao;
import com.desmond.entity.Shop;
import com.desmond.service.ShopService;
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
    public ResponseResult<List<Shop>> getAllShop() {
        return new ResponseResult<>(HttpStatus.OK.value(), shopDao.getAllShop());
    }

    @Override
    public ResponseResult<List<Shop>> getShopByUserId(String userId) {
        if (Objects.isNull(userId)) {
            return new ResponseResult<>(HttpStatus.INTERNAL_SERVER_ERROR.value(), "用户id不得为空");
        }
        try {
            return new ResponseResult<>(HttpStatus.OK.value(), shopDao.getShopByUserId(userId));
        } catch (Exception e) {
            logger.error("获取商店异常: " + e.getMessage());
            return new ResponseResult<>(HttpStatus.INTERNAL_SERVER_ERROR.value(), "获取失败，请稍后再试");
        }
    }

    @Override
    public ResponseResult<List<Shop>> getShopByCategory(String category) {
        try {
            return new ResponseResult<>(HttpStatus.OK.value(), shopDao.getShopByCategory(category));
        } catch (Exception e) {
            logger.error("获取商店异常: " + e.getMessage());
            return new ResponseResult<>(HttpStatus.INTERNAL_SERVER_ERROR.value(), "获取失败，请稍后再试");
        }
    }

    @Override
    public ResponseResult<String> addShop(Shop shop) {
        if (Objects.isNull(shop.getUserId())) {
            return new ResponseResult<>(HttpStatus.INTERNAL_SERVER_ERROR.value(), "用户id不得为空");
        }
        try {
            shop.setId(GenUUID.getUUID());
            shopDao.addShop(shop);
            return new ResponseResult<>(HttpStatus.OK.value(), "操作成功");
        } catch (Exception e) {
            logger.error("创建商店异常: " + e.getMessage());
            return new ResponseResult<>(HttpStatus.INTERNAL_SERVER_ERROR.value(), "操作失败，请稍后再试");
        }
    }

    @Override
    public ResponseResult<String> updateShop(Shop shop) {
        if (Objects.isNull(shop.getUserId()) || Objects.isNull(shop.getId())) {
            return new ResponseResult<>(HttpStatus.INTERNAL_SERVER_ERROR.value(), "id不得为空");
        }
        try {
            shopDao.updateShop(shop);
            return new ResponseResult<>(HttpStatus.OK.value(), "操作成功");
        } catch (Exception e) {
            logger.error("更新商店异常: " + e.getMessage());
            return new ResponseResult<>(HttpStatus.INTERNAL_SERVER_ERROR.value(), "操作失败，请稍后再试");
        }
    }

    @Override
    public ResponseResult<String> deleteShopById(String shopId) {
        if (Objects.isNull(shopId)) {
            return new ResponseResult<>(HttpStatus.INTERNAL_SERVER_ERROR.value(), "商店id不得为空");
        }
        try {
            shopDao.deleteShopById(shopId);
            return new ResponseResult<>(HttpStatus.OK.value(), "操作成功");
        } catch (Exception e) {
            logger.error("删除商店异常: " + e.getMessage());
            return new ResponseResult<>(HttpStatus.INTERNAL_SERVER_ERROR.value(), "操作失败，请稍后再试");
        }
    }
}
