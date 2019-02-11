package com.sam.pose.server.impl;

import com.sam.pose.bean.CameraInfo;
import com.sam.pose.dao.CameraInfoRepository;
import com.sam.pose.server.CameraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CameraServiceImpl implements CameraService {

    @Autowired
    private CameraInfoRepository cameraInfoRepository;
    @Override
    public void save(CameraInfo info) {
        cameraInfoRepository.save(info);

    }

    @Override
    public Iterable<CameraInfo> findAll() {
        return cameraInfoRepository.findAll();
    }

    @Override
    public void deleteAll() {
        cameraInfoRepository.deleteAll();
    }


}
