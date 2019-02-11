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
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "cameraSetting")
@DocumentIndexingPolicy(mode = IndexingMode.Consistent)
public class CameraSetting implements Serializable {
    @Id
    private String settingId;
    private String cameraId;
    @PartitionKey
    private String partitionId;

    private String compressionRatio;
    private String vedioFps;
    private List<Integer>colorMin;
    private List<Integer>colorMax;
    private String storeId;
}
