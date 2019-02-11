package com.sam.pose.server.impl;

import com.sam.pose.bean.AlertInfo;
import com.sam.pose.dao.AlertInfoRepository;
import com.sam.pose.server.AlertService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AlertServiceImpl implements AlertService {

    @Autowired
    private AlertInfoRepository alertInfoRepository;

    @Override
    public Iterable<AlertInfo> findAll() {
        return alertInfoRepository.findAll();
    }

    @Override
    public void save(AlertInfo alertInfo) {
        alertInfoRepository.save(alertInfo);
    }
}
