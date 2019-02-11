package com.sam.pose.dao;

import com.microsoft.azure.spring.data.cosmosdb.repository.DocumentDbRepository;
import com.sam.pose.bean.StoreInfo;
import org.springframework.stereotype.Repository;

@Repository
public interface StoreInfoRepository extends DocumentDbRepository<StoreInfo,String> {
}
