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
@Document(collection = "cameraInfo")
@DocumentIndexingPolicy(mode = IndexingMode.Consistent)
public class CameraInfo implements Serializable {
    @Id
    private String cameraId;
    private String clubId;
    @PartitionKey
    private String partitionId;
    private String compressionRatio;
    private String vedioFps;
    private Integer[]colorMin;
    private Integer[]colorMax;
    private String cameraName;
    private String resourceId;
    private String idName;//方便查看,storeId_resourceId
    private String isMonitor;//0为不监控，1为监控
}
