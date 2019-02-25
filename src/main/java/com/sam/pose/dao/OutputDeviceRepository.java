package com.sam.pose.dao;

import com.microsoft.azure.spring.data.cosmosdb.repository.DocumentDbRepository;
import com.sam.pose.bean.OutputDeviceInfo;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OutputDeviceRepository extends DocumentDbRepository<OutputDeviceInfo,String> {
    public List<OutputDeviceInfo>findByName(String name);
    public List<OutputDeviceInfo>findByDeviceId(String name);
    public List<OutputDeviceInfo>findByClubId(String clubId);

}
