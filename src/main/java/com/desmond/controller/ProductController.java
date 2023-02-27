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

    @GetMapping("/anonymous/products/{shopId}")
    public ResponseResult<List<Product>> getProductsByShopId(@PathVariable("shopId") String shopId) {
        try {
            return ResponseResult.success("操作成功", productService.getProductsByShopId(shopId));
        } catch (Exception e) {
            return ResponseResult.fail();
        }
    }

    @PreAuthorize("hasAnyAuthority('system:merchant', 'system:admin')")
    @PostMapping("/merchant/product")
    public ResponseResult<String> addProduct(@RequestBody Product product) {
        try {
            productService.addProduct(product);
            return ResponseResult.success();
        } catch (Exception e) {
            return ResponseResult.fail();
        }
    }

    @PreAuthorize("hasAnyAuthority('system:merchant', 'system:admin')")
    @PutMapping("/merchant/product")
    public ResponseResult<String> updateProduct(@RequestBody Product product) {
        try {
            productService.updateProduct(product);
            return ResponseResult.success();
        } catch (Exception e) {
            return ResponseResult.fail();
        }
    }

    @PreAuthorize("hasAnyAuthority('system:merchant', 'system:admin')")
    @DeleteMapping("/merchant/product/{productId}")
    public ResponseResult<String> deleteProductById(@PathVariable("productId") String productId) {
        try {
            productService.deleteProductById(productId);
            return ResponseResult.success();
        } catch (Exception e) {
            return ResponseResult.fail();
        }
    }
}
