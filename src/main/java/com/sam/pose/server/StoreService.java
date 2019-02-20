package com.sam.pose.server;

import com.sam.pose.bean.ClubInfo;


public interface StoreService {
    Iterable<ClubInfo>findAll();
    void save(ClubInfo storeInfo);
}
