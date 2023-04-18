package com.desmond.service.impl;

import com.desmond.dao.AddressInfoDao;
import com.desmond.entity.AddressInfo;
import com.desmond.service.AddressInfoService;
import com.desmond.utils.GenUUID;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class AddressInfoServiceImpl implements AddressInfoService {
    Logger logger = LoggerFactory.getLogger(AddressInfoServiceImpl.class);
    @Autowired
    private AddressInfoDao addressInfoDao;

    @Override
    public List<AddressInfo> getAddressInfoByUserId(String userId) {
        if (Objects.isNull(userId)) {
            throw new RuntimeException("用户id不得为空");
        }
        try {
            return addressInfoDao.getAddressInfoByUserId(userId);
        } catch (Exception e) {
            logger.error("获取用户收获地址异常", e);
            throw new RuntimeException("获取失败，请稍后再试");
        }
    }

    @Override
    public void addAddressInfo(AddressInfo addressInfo) {
        if (Objects.isNull(addressInfo.getUserId())) {
            throw new RuntimeException("用户id不得为空");
        }
        try {
            addressInfo.setId(GenUUID.getUUID());
            addressInfoDao.addAddressInfo(addressInfo);
        } catch (Exception e) {
            logger.error("添加地址异常", e);
            throw new RuntimeException("添加地址异常，请稍后再试");
        }
    }

    @Override
    public void updateAddressInfo(AddressInfo addressInfo){
            if (Objects.isNull(addressInfo.getUserId()) && Objects.isNull(addressInfo.getId())){
                throw new RuntimeException("id不得为空");
            }
            try {
                addressInfoDao.updateAddressInfo(addressInfo);
            } catch (Exception e) {
                logger.error("更新地址异常", e);
                throw new RuntimeException("更新地址异常，请稍后再试");
            }
        }

    @Override
    public void deleteAddressInfoById(String id) {
        if (Objects.isNull(id)) {
            throw new RuntimeException("id不得为空");
        }
        try {
            addressInfoDao.deleteAddressInfoById(id);
        } catch (Exception e) {
            logger.error("删除地址异常", e);
            throw new RuntimeException("删除地址异常，请稍后再试");
        }
    }
}
