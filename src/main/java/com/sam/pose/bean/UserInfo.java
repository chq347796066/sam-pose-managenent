package com.sam.pose.bean;


import com.microsoft.azure.documentdb.IndexingMode;
import com.microsoft.azure.spring.data.cosmosdb.core.mapping.Document;
import com.microsoft.azure.spring.data.cosmosdb.core.mapping.DocumentIndexingPolicy;
import com.microsoft.azure.spring.data.cosmosdb.core.mapping.PartitionKey;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "userInfo")
@DocumentIndexingPolicy(mode = IndexingMode.Consistent)
public class UserInfo implements Serializable {

    @Id
    private String userId;
    private String userName;
    private String userPwd;
    @PartitionKey
    private String partitionId;






}
