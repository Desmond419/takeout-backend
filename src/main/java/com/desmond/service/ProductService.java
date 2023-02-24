package com.desmond.service;

import com.desmond.common.ResponseResult;
import com.desmond.entity.Product;

import java.util.List;

public interface ProductService {
    List<Product> getProductsByShopId(String shopId);
    void addProduct(Product product);
    void updateProduct(Product product);
    void deleteProductById(String prodId);
}
