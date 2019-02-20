package com.sam.pose.server.impl;

import com.sam.pose.bean.ClubInfo;
import com.sam.pose.dao.ClubInfoRepository;
import com.sam.pose.server.StoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StoreServiceImpl implements StoreService {

    @Autowired
    private ClubInfoRepository storeInfoRepository;
    @Override
    public Iterable<ClubInfo> findAll() {
        return storeInfoRepository.findAll();
    }

    @Override
    public void save(ClubInfo storeInfo) {
        storeInfoRepository.save(storeInfo);
    }

}
