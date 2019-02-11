package com.sam.pose.parameter;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SettingBean {
    private String cameraId;
    private String vedioFps;
    private String compressionRatio;
    private List<Integer> colorMin;
    private List<Integer> colorMax;
}
