package com.sam.pose.dao;

import com.microsoft.azure.spring.data.cosmosdb.repository.DocumentDbRepository;
import com.sam.pose.bean.CameraInfo;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CameraInfoRepository extends DocumentDbRepository<CameraInfo,String> {
    List<CameraInfo> findByCameraId(String cameraId);
}
