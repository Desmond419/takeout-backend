package com.desmond.controller;

import com.desmond.common.ResponseResult;
import com.desmond.entity.Shop;
import com.desmond.service.ShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ShopController {

    @Autowired
    private ShopService shopService;

    @GetMapping("/user/shops")
    public ResponseResult<List<Shop>> getAllShop() {
        return shopService.getAllShop();
    }

    @GetMapping("/user/shops/{category}")
    public ResponseResult<List<Shop>> getShopByCategory(@PathVariable("category") String category) {
        return shopService.getShopByCategory(category);
    }

    @PreAuthorize("hasAnyAuthority('system:merchant', 'system:admin')")
    @GetMapping("/merchant/shops/{userId}")
    public ResponseResult<List<Shop>> getShopByUserId(@PathVariable("userId") String userId) {
        return shopService.getShopByUserId(userId);
    }

    @PreAuthorize("hasAnyAuthority('system:merchant', 'system:admin')")
    @PostMapping("/merchant/shop")
    public ResponseResult<String> addShop(@RequestBody Shop shop) {
        return shopService.addShop(shop);
    }

    @PreAuthorize("hasAnyAuthority('system:merchant', 'system:admin')")
    @PutMapping("/merchant/shop")
    public ResponseResult<String> updateShopById(@RequestBody Shop shop) {
        return shopService.updateShop(shop);
    }

    @PreAuthorize("hasAnyAuthority('system:merchant', 'system:admin')")
    @DeleteMapping("/merchant/shop/{shopId}")
    public ResponseResult<String> deleteShopById(@PathVariable("shopId") String shopId) {
        return shopService.deleteShopById(shopId);
    }
}
