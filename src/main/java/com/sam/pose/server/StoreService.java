package com.sam.pose.server;

import com.sam.pose.bean.StoreInfo;


public interface StoreService {
    Iterable<StoreInfo>findAll();
    void save(StoreInfo storeInfo);
}
