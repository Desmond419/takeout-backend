package com.desmond.controller;

import com.desmond.common.ResponseResult;
import com.desmond.entity.AddressInfo;
import com.desmond.service.AddressInfoService;
import com.desmond.utils.BusinessException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class AddressInfoController {
    @Autowired
    private AddressInfoService addressInfoService;

    @GetMapping("/user/address-info/{userId}")
    public ResponseResult<List<AddressInfo>> getAddressByUserId(@PathVariable("userId") String userId) {
        try {
            return ResponseResult.success("操作成功", addressInfoService.getAddressInfoByUserId(userId));
        } catch (BusinessException e) {
            return ResponseResult.fail(e.getMessage());
        }
    }

    @PostMapping("/user/address-info")
    public ResponseResult<String> addAddressInfo(@RequestBody AddressInfo addressInfo) {
        try {
            addressInfoService.addAddressInfo(addressInfo);
            return ResponseResult.success();
        } catch (BusinessException e) {
            return ResponseResult.fail(e.getMessage());
        }
    }

    @PutMapping("/user/address-info")
    public ResponseResult<String> updateAddressInfo(@RequestBody AddressInfo addressInfo) {
        try {
            addressInfoService.updateAddressInfo(addressInfo);
            return ResponseResult.success();
        } catch (BusinessException e) {
            return ResponseResult.fail(e.getMessage());
        }
    }

    /**
     * 删除指定地址信息
     *
     * @param id addressInfo Id
     */
    @DeleteMapping("/user/address-info/{id}")
    public ResponseResult<String> deleteAddressInfoById(@PathVariable("id") String id) {
        try {
            addressInfoService.deleteAddressInfoById(id);
            return ResponseResult.success();
        } catch (BusinessException e) {
            return ResponseResult.fail(e.getMessage());
        }
    }
}
