package com.desmond.service;

import com.desmond.common.ResponseResult;
import com.desmond.entity.Product;

import java.util.List;

public interface ProductService {
    ResponseResult<List<Product>> getProductsByShopId(String shopId);
    ResponseResult<String> addProduct(Product product);
    ResponseResult<String> updateProduct(Product product);
    ResponseResult<String> deleteProductById(String prodId);
}
