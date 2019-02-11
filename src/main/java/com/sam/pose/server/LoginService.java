package com.sam.pose.server;

import com.sam.pose.bean.UserInfo;

import java.util.List;

public interface LoginService {
    List<UserInfo> findByUserId(String userId);
}
