package com.desmond.dao;

import com.desmond.entity.Product;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ProductDao {
    List<Product> getProductsByShopId(String shopId);
    void addProduct(Product product);
    void updateProduct(Product product);
    void deleteProductById(String prodId);
}
