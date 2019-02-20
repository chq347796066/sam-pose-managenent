package com.sam.pose.bean;


import com.microsoft.azure.spring.data.cosmosdb.core.mapping.Document;
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
@Document(collection = "outputDeviceInfo")
public class OutputDeviceInfo {
    @Id
    private String deviceId;
    private String name;
    @PartitionKey
    private String partitionId;
}
