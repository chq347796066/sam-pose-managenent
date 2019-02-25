package com.sam.pose.dao;

import com.microsoft.azure.spring.data.cosmosdb.repository.DocumentDbRepository;
import com.sam.pose.bean.RuleInfo;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RuleInfoRepository extends DocumentDbRepository<RuleInfo,String> {
    public List<RuleInfo> findByRuleId(String ruleId);
}
