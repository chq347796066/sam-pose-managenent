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
@Document(collection = "openpose_logs")
public class OpenposeLogs {
    @Id
    private String id;
    @PartitionKey
    private String key;
    private String r_HipAngle;
    private String l_HipAngle;
    private String r_KneeAngle;
    private String r_LenRate;
    private String l_KneeAngle;
    private String l_LenRate;
    private String result;
    private String up_result;
    private String clubId;
    private String cameraId;
    private String image;
    private String time;

}