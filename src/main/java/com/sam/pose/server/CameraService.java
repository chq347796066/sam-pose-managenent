package com.sam.pose.server;

import com.sam.pose.bean.CameraInfo;

public interface CameraService {
    void save(CameraInfo info);
    Iterable<CameraInfo>findAll();
    void deleteAll();
    void changeMonitorCam(String[]cameraIds);

    void deleteMonitorCam(String cameraId);
}
