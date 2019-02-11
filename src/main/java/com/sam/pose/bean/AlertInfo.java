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
@Document(collection = "alertInfo")
@DocumentIndexingPolicy(mode = IndexingMode.Consistent)
public class AlertInfo implements Serializable {
    @Id
    private String alertId;
    @PartitionKey
    private String partitionId;
    private String cameraId;
    private String storeId;
    private String image;
    private String time;




    @Override
    public String toString() {
        return "AlertInfo{" +
                "alertId='" + alertId + '\'' +
                ", partitionId='" + partitionId + '\'' +
                ", cameraId='" + cameraId + '\'' +
                ", storeId='" + storeId + '\'' +
                ", time='" + time + '\'' +
                '}';
    }
}
