package com.desmond.dao;

import com.desmond.entity.AddressInfo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface AddressInfoDao {
    List<AddressInfo> getAddressInfoByUserId(String userId);
    void addAddressInfo(AddressInfo addressInfo);
    void updateAddressInfo(AddressInfo addressInfo);
    void deleteAddressInfoById(String id);
}
