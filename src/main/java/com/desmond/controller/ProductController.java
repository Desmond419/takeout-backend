package com.desmond.controller;

import com.desmond.common.ResponseResult;
import com.desmond.entity.Product;
import com.desmond.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
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
        return productService.getProductsByShopId(shopId);
    }

    @PreAuthorize("hasAnyAuthority('system:merchant', 'system:admin')")
    @PostMapping("/merchant/product")
    public ResponseResult<String> addProduct(@RequestBody Product product) {
        return productService.addProduct(product);
    }

    @PreAuthorize("hasAnyAuthority('system:merchant', 'system:admin')")
    @PutMapping("/merchant/product")
    public ResponseResult<String> updateProduct(@RequestBody Product product) {
        return productService.updateProduct(product);
    }

    @PreAuthorize("hasAnyAuthority('system:merchant', 'system:admin')")
    @DeleteMapping("/merchant/product/{productId}")
    public ResponseResult<String> deleteProductById(@PathVariable("productId") String productId) {
        return productService.deleteProductById(productId);
    }
}
