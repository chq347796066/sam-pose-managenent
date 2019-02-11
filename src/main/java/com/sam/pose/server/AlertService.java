package com.sam.pose.server;

import com.sam.pose.bean.AlertInfo;

public interface AlertService {
    Iterable<AlertInfo>findAll();
    void save(AlertInfo alertInfo);
}
