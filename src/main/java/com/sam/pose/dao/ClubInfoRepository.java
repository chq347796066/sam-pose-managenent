package com.sam.pose.dao;

import com.microsoft.azure.spring.data.cosmosdb.repository.DocumentDbRepository;
import com.sam.pose.bean.ClubInfo;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClubInfoRepository extends DocumentDbRepository<ClubInfo,String> {

    public List<ClubInfo> findByClubId(String storeId);
}
