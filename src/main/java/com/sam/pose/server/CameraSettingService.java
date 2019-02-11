package com.sam.pose.server;

import com.sam.pose.bean.CameraInfo;
import com.sam.pose.bean.CameraSetting;

public interface CameraSettingService {
    void save(CameraSetting cameraSetting);
    void deleteAll();
    CameraInfo findByCameraId(String cameraId);
    Iterable<CameraSetting>findAll();
}
