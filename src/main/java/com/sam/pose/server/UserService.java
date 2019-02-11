package com.sam.pose.server;

import com.sam.pose.bean.UserInfo;

import java.util.List;

public interface UserService {
    void save(UserInfo userInfo);
    Iterable<UserInfo>findAll();
    UserInfo findByUserName(String userName);
}
