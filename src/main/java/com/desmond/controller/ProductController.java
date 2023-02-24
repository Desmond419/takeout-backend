package com.desmond.controller;

import com.desmond.common.ResponseResult;
import com.desmond.entity.Product;
import com.desmond.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ProductController {
    @Autowired
    private ProductService productService;

    @GetMapping("/user/products/{shopId}")
    public ResponseResult<List<Product>> getProductsByShopId(@PathVariable("shopId") String shopId) {
        try {
            return new ResponseResult<>(HttpStatus.OK.value(), productService.getProductsByShopId(shopId));
        } catch (Exception e) {
            return new ResponseResult<>(HttpStatus.INTERNAL_SERVER_ERROR.value(), "服务器异常，请稍后再试");
        }
    }

    @PreAuthorize("hasAnyAuthority('system:merchant', 'system:admin')")
    @PostMapping("/merchant/product")
    public ResponseResult<String> addProduct(@RequestBody Product product) {
        try {
            productService.addProduct(product);
            return new ResponseResult<>(HttpStatus.CREATED.value(), "操作成功");
        } catch (Exception e) {
            return new ResponseResult<>(HttpStatus.INTERNAL_SERVER_ERROR.value(), "服务器异常，请稍后再试");
        }
    }

    @PreAuthorize("hasAnyAuthority('system:merchant', 'system:admin')")
    @PutMapping("/merchant/product")
    public ResponseResult<String> updateProduct(@RequestBody Product product) {
        try {
            productService.updateProduct(product);
            return new ResponseResult<>(HttpStatus.OK.value(), "操作成功");
        } catch (Exception e) {
            return new ResponseResult<>(HttpStatus.INTERNAL_SERVER_ERROR.value(), "服务器异常，请稍后再试");
        }
    }

    @PreAuthorize("hasAnyAuthority('system:merchant', 'system:admin')")
    @DeleteMapping("/merchant/product/{productId}")
    public ResponseResult<String> deleteProductById(@PathVariable("productId") String productId) {
        try {
            productService.deleteProductById(productId);
            return new ResponseResult<>(HttpStatus.OK.value(), "操作成功");
        } catch (Exception e) {
            return new ResponseResult<>(HttpStatus.INTERNAL_SERVER_ERROR.value(), "服务器异常，请稍后再试");
        }
    }
}
