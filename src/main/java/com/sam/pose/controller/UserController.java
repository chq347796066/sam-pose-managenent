package com.sam.pose.controller;

import com.sam.pose.bean.Result;
import com.sam.pose.bean.UserInfo;
import com.sam.pose.dao.UserInfoRepository;
import com.sam.pose.util.Md5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class UserController {


    @Autowired
    private UserInfoRepository userInfoRepository;

    @RequestMapping(value = "/findAllUsers",method = RequestMethod.POST)
    @ResponseBody
    public List<UserInfo> findAllUser(){
        List<UserInfo>userInfos=userInfoRepository.findByUserCharacter("1");
        userInfos.forEach(t-> System.out.println(t));
        return userInfos;
    }

    @RequestMapping(value = "/changePwd",method = RequestMethod.POST)
    @ResponseBody
    public Result changePwd(String userId, String password){
        System.out.println("userId:"+userId+",password:"+password);
        try {
            List<UserInfo> userInfos = userInfoRepository.findByUserId(userId);
            if (userInfos != null && userInfos.size() > 0) {
                UserInfo userInfo = userInfos.get(0);
                userInfo.setUserPwd(Md5Util.md5(password));
                userInfoRepository.save(userInfo);
                return new Result("0", "Successfully modified");
            } else {
                return new Result("1", "User does not exist");
            }
        }catch (Exception e){
            return new Result("2","Network error");
        }
    }

    @RequestMapping(value = "/addUser",method = RequestMethod.POST)
    @ResponseBody
    public Result addUser(String userName, String password){
        try{
            System.out.println("userName:"+userName+",password:"+password);
            UserInfo userInfo=new UserInfo(null,userName,password,"1","1");
            userInfoRepository.save(userInfo);
            return new Result("0","Added successfully");
        }catch (Exception e) {
            System.out.println(e);
            return new Result("1","Network error");
        }


}






    @RequestMapping("/addUser")
    @ResponseBody
    public String add(){
        UserInfo userInfo=new UserInfo("1","chenhaiquan",Md5Util.md5("123456"),"0","1");
        userInfoRepository.save(userInfo);
        UserInfo userInfo1=new UserInfo("2","Gene",Md5Util.md5("123456"),"1","1");
        userInfoRepository.save(userInfo1);
        UserInfo userInfo2=new UserInfo("3","Woody",Md5Util.md5("123456"),"1","1");
        userInfoRepository.save(userInfo2);
        return "success";
    }


}
