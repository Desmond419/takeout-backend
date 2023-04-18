package com.desmond.service;


import com.desmond.entity.AddressInfo;

import java.util.List;

public interface AddressInfoService {
    List<AddressInfo> getAddressInfoByUserId(String userId);
    void addAddressInfo(AddressInfo addressInfo);
    void updateAddressInfo(AddressInfo addressInfo);
    void deleteAddressInfoById(String id);
}
