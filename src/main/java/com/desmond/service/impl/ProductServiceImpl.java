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
    public List<Product> getProductsByShopId(String shopId) {
        if (Objects.isNull(shopId)) {
            throw new RuntimeException("商店id不得为空");
        }
        try {
            return productDao.getProductsByShopId(shopId);
        } catch (Exception e) {
            logger.error("获取商品异常", e);
            throw new RuntimeException("获取失败，请稍后再试");
        }
    }

    @Override
    public void addProduct(Product product) {
        if (Objects.isNull(product.getShopId())) {
            throw new RuntimeException("商店id不得为空");
        }
        try {
            product.setId(GenUUID.getUUID());
            productDao.addProduct(product);
        } catch (Exception e) {
            logger.error("添加商品异常", e);
            throw new RuntimeException("操作失败，请稍后再试");
        }
    }

    @Override
    public void updateProduct(Product product) {
        if (Objects.isNull(product.getShopId()) || Objects.isNull(product.getId())) {
            throw new RuntimeException("id不得为空");
        }
        try {
            productDao.updateProduct(product);
        } catch (Exception e) {
            logger.error("更新商品异常", e);
            throw new RuntimeException("操作失败，请稍后再试");
        }
    }

    @Override
    public void deleteProductById(String prodId) {
        if (Objects.isNull(prodId)) {
            throw new RuntimeException("商品id不得为空");
        }
        try {
            productDao.deleteProductById(prodId);
        } catch (Exception e) {
            logger.error("删除商品异常", e);
            throw new RuntimeException("操作失败，请稍后再试");
        }
    }
}
