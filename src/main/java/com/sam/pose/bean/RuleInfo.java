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


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "ruleInfo")
@DocumentIndexingPolicy(mode = IndexingMode.Consistent)
public class RuleInfo {
    @Id
    private String ruleId;
    private String cameraId;
    private String deviceId;
    @PartitionKey
    private String partitionId;
}
