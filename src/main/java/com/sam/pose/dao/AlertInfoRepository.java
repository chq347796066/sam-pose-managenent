package com.sam.pose.dao;

import com.microsoft.azure.spring.data.cosmosdb.repository.DocumentDbRepository;
import com.sam.pose.bean.AlertInfo;
import org.springframework.stereotype.Repository;

@Repository
public interface AlertInfoRepository extends DocumentDbRepository<AlertInfo,String> {

}
