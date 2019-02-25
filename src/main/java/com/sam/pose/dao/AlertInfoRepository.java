package com.sam.pose.dao;

import com.microsoft.azure.spring.data.cosmosdb.repository.DocumentDbRepository;
import com.sam.pose.bean.AlertInfo;
import javafx.scene.control.Alert;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AlertInfoRepository extends DocumentDbRepository<AlertInfo,String> {

    List<AlertInfo> findByClubId(String clubId);
    List<AlertInfo>findByAlertId(String key);

}
