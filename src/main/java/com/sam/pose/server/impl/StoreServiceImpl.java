package com.sam.pose.server.impl;

import com.sam.pose.bean.StoreInfo;
import com.sam.pose.dao.StoreInfoRepository;
import com.sam.pose.server.StoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StoreServiceImpl implements StoreService {

    @Autowired
    private StoreInfoRepository storeInfoRepository;
    @Override
    public Iterable<StoreInfo> findAll() {
        return storeInfoRepository.findAll();
    }

    @Override
    public void save(StoreInfo storeInfo) {
        storeInfoRepository.save(storeInfo);
    }

}
