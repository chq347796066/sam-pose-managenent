package com.sam.pose.server.impl;

import com.sam.pose.bean.CameraInfo;
import com.sam.pose.dao.CameraInfoRepository;
import com.sam.pose.server.CameraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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

    @Override
    public void changeMonitorCam(String[] cameraIds) {
        for(String cameraId:cameraIds){
            List<CameraInfo> cameraInfos=cameraInfoRepository.findByCameraId(cameraId);
            if(cameraInfos!=null&&cameraInfos.size()>0){
                CameraInfo cameraInfo=cameraInfos.get(0);
                cameraInfo.setIsMonitor("1");
                cameraInfoRepository.save(cameraInfo);
            }

        }
    }

    @Override
    public void deleteMonitorCam(String cameraId) {
        List<CameraInfo>cameraInfos=cameraInfoRepository.findByCameraId(cameraId);
        if(cameraInfos!=null&&cameraInfos.size()>0){
            CameraInfo cameraInfo=cameraInfos.get(0);
            cameraInfo.setIsMonitor("0");
            cameraInfoRepository.save(cameraInfo);
        }
    }


}
