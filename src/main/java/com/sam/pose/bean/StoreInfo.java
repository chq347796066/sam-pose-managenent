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
@Document(collection = "storeInfo")
@DocumentIndexingPolicy(mode = IndexingMode.Consistent)
public class StoreInfo implements Serializable {
    @Id
    private String storeId;
    private String storeName;
    private String sitResourceId;
    @PartitionKey
    private String partitionId;




}
