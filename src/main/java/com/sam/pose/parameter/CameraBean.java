package com.sam.pose.parameter;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CameraBean {
    private String cameraId;
    private String compressionRatio;
    private String vedioFps;
    private Integer[]colorMin;
    private Integer[]colorMax;
    private String []deviceIds;//输出的id
}
