package com.sam.pose.dao;

import com.microsoft.azure.spring.data.cosmosdb.repository.DocumentDbRepository;
import com.sam.pose.bean.ItemServiceBO;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface ItemServiceRepository extends DocumentDbRepository<ItemServiceBO,String> {
    List<ItemServiceBO> findByClubId(String clubId);
    List<ItemServiceBO>findByServiceType(String serviceType);


}
