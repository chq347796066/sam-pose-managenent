package com.sam.pose.dao;

import com.microsoft.azure.spring.data.cosmosdb.repository.DocumentDbRepository;
import com.sam.pose.bean.UserInfo;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserInfoRepository extends DocumentDbRepository<UserInfo,String> {
    List<UserInfo> findByUserId(String userId);
    List<UserInfo> findByUserName(String userName);
    List<UserInfo>findByUserCharacter(String userCharacter);
}
