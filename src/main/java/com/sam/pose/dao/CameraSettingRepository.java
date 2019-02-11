package com.sam.pose.dao;

import com.microsoft.azure.spring.data.cosmosdb.repository.DocumentDbRepository;
import com.sam.pose.bean.CameraInfo;
import com.sam.pose.bean.CameraSetting;
import org.springframework.stereotype.Repository;


@Repository
public interface CameraSettingRepository extends DocumentDbRepository<CameraSetting,String> {

}
