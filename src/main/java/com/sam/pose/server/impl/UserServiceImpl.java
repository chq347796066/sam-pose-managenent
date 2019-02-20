package com.sam.pose.server.impl;

import com.sam.pose.bean.UserInfo;
import com.sam.pose.dao.UserInfoRepository;
import com.sam.pose.server.UserService;
import com.sam.pose.util.Md5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserInfoRepository userInfoRepository;
    @Override
    public void save(UserInfo userInfo) {
        userInfoRepository.save(userInfo);
    }

    @Override
    public Iterable<UserInfo> findAll() {
        System.out.println("findAll user");
        return userInfoRepository.findAll();
    }

    @Override
    public UserInfo findByUserName(String userName) {
        List<UserInfo>userInfos= userInfoRepository.findByUserName(userName);
        if(userInfos!=null&&userInfos.size()>0){
            return userInfos.get(0);
        }
        return null;
    }
}
