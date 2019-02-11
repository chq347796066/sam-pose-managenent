package com.sam.pose.server.impl;

import com.sam.pose.bean.UserInfo;
import com.sam.pose.dao.UserInfoRepository;
import com.sam.pose.server.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    private UserInfoRepository repository;

    @Override
    public List<UserInfo> findByUserId(String userId) {
        return repository.findByUserId(userId);
    }
}
