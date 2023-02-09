package com.desmond.service.impl;

import com.desmond.common.ResponseResult;
import com.desmond.dao.ProductDao;
import com.desmond.entity.Product;
import com.desmond.service.ProductService;
import com.desmond.utils.GenUUID;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class ProductServiceImpl implements ProductService {
    Logger logger = LoggerFactory.getLogger(ProductServiceImpl.class);

    @Autowired
    private ProductDao productDao;

    @Override
    public ResponseResult<List<Product>> getProductsByShopId(String shopId) {
        if (Objects.isNull(shopId)) {
            return new ResponseResult<>(HttpStatus.INTERNAL_SERVER_ERROR.value(), "商店id不得为空");
        }
        try {
            return new ResponseResult<>(HttpStatus.OK.value(), productDao.getProductsByShopId(shopId));
        } catch (Exception e) {
            logger.error("获取商品异常: " + e.getMessage());
            return new ResponseResult<>(HttpStatus.INTERNAL_SERVER_ERROR.value(), "获取失败，请稍后再试");
        }
    }

    @Override
    public ResponseResult<String> addProduct(Product product) {
        if (Objects.isNull(product.getShopId())) {
            return new ResponseResult<>(HttpStatus.INTERNAL_SERVER_ERROR.value(), "商店id不得为空");
        }
        try {
            product.setId(GenUUID.getUUID());
            productDao.addProduct(product);
            return new ResponseResult<>(HttpStatus.OK.value(), "操作成功");
        } catch (Exception e) {
            logger.error("添加商品异常: " + e.getMessage());
            return new ResponseResult<>(HttpStatus.INTERNAL_SERVER_ERROR.value(), "操作失败，请稍后再试");
        }
    }

    @Override
    public ResponseResult<String> updateProduct(Product product) {
        if (Objects.isNull(product.getShopId()) || Objects.isNull(product.getId())) {
            return new ResponseResult<>(HttpStatus.INTERNAL_SERVER_ERROR.value(), "id不得为空");
        }
        try {
            productDao.updateProduct(product);
            return new ResponseResult<>(HttpStatus.OK.value(), "操作成功");
        } catch (Exception e) {
            logger.error("更新商品异常: " + e.getMessage());
            return new ResponseResult<>(HttpStatus.INTERNAL_SERVER_ERROR.value(), "操作失败，请稍后再试");
        }
    }

    @Override
    public ResponseResult<String> deleteProductById(String prodId) {
        if (Objects.isNull(prodId)) {
            return new ResponseResult<>(HttpStatus.INTERNAL_SERVER_ERROR.value(), "商品id不得为空");
        }
        try {
            productDao.deleteProductById(prodId);
            return new ResponseResult<>(HttpStatus.OK.value(), "操作成功");
        } catch (Exception e) {
            logger.error("删除商品异常: " + e.getMessage());
            return new ResponseResult<>(HttpStatus.INTERNAL_SERVER_ERROR.value(), "操作失败，请稍后再试");
        }
    }
}
