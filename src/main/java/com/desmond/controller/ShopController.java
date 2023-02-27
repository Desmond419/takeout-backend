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

    @GetMapping("/anonymous/shops")
    public ResponseResult<List<Shop>> getAllShop() {
        try {
            return ResponseResult.success("操作成功", shopService.getAllShop());
        } catch (Exception e) {
            return ResponseResult.fail();
        }
    }

    @GetMapping("/anonymous/shops/{category}")
    public ResponseResult<List<Shop>> getShopByCategory(@PathVariable("category") String category) {
        try {
            return ResponseResult.success("操作成功", shopService.getShopByCategory(category));
        } catch (Exception e) {
            return ResponseResult.fail();
        }
    }

    @PreAuthorize("hasAnyAuthority('system:merchant', 'system:admin')")
    @GetMapping("/merchant/shops/{userId}")
    public ResponseResult<List<Shop>> getShopByUserId(@PathVariable("userId") String userId) {
        try {
            return ResponseResult.success("操作成功", shopService.getShopByUserId(userId));
        } catch (Exception e) {
            return ResponseResult.fail();
        }
    }

    @PreAuthorize("hasAnyAuthority('system:merchant', 'system:admin')")
    @PostMapping("/merchant/shop")
    public ResponseResult<String> addShop(@RequestBody Shop shop) {
        try {
            shopService.addShop(shop);
            return ResponseResult.success();
        } catch (Exception e) {
            return ResponseResult.fail();
        }
    }

    @PreAuthorize("hasAnyAuthority('system:merchant', 'system:admin')")
    @PutMapping("/merchant/shop")
    public ResponseResult<String> updateShopById(@RequestBody Shop shop) {
        try {
            shopService.updateShop(shop);
            return ResponseResult.success();
        } catch (Exception e) {
            return ResponseResult.fail();
        }
    }

    @PreAuthorize("hasAnyAuthority('system:merchant', 'system:admin')")
    @DeleteMapping("/merchant/shop/{shopId}")
    public ResponseResult<String> deleteShopById(@PathVariable("shopId") String shopId) {
        try {
            shopService.deleteShopById(shopId);
            return ResponseResult.success();
        } catch (Exception e) {
            return ResponseResult.fail();
        }
    }
}
