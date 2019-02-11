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
@Document(collection = "itemService")
@DocumentIndexingPolicy(mode = IndexingMode.Consistent)
public class ItemServiceBO {

    @Id
    private String itemId;
    @PartitionKey
    private String clubId;
    private String serviceType;
    private String description;
    private String servicePrice;

    @Override
    public String toString() {
        return "ItemServiceBO{" +
                "itemId='" + itemId + '\'' +
                ", clubId='" + clubId + '\'' +
                ", serviceType='" + serviceType + '\'' +
                ", description='" + description + '\'' +
                ", servicePrice='" + servicePrice + '\'' +
                '}';
    }
}
