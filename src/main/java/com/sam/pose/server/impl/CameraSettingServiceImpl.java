package com.sam.pose.server.impl;

import com.sam.pose.bean.CameraInfo;
import com.sam.pose.bean.CameraSetting;
import com.sam.pose.dao.CameraInfoRepository;
import com.sam.pose.dao.CameraSettingRepository;
import com.sam.pose.server.CameraSettingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CameraSettingServiceImpl implements CameraSettingService {

    @Autowired
    private CameraSettingRepository cameraSettingRepository;

    @Autowired
    private CameraInfoRepository cameraInfoRepository;
    @Override
    public void save(CameraSetting cameraSetting) {
        cameraSettingRepository.save(cameraSetting);

    }

    @Override
    public void deleteAll() {
        cameraSettingRepository.deleteAll();
    }

    @Override
    public CameraInfo findByCameraId(String cameraId) {
        List<CameraInfo>cameraInfos=cameraInfoRepository.findByCameraId(cameraId);
        if(cameraInfos!=null&&cameraInfos.size()>0){
            return cameraInfos.get(0);
        }
        return null;
    }

    @Override
    public Iterable<CameraSetting> findAll() {
        return cameraSettingRepository.findAll();
    }
}
