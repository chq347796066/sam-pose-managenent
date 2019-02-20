package com.sam.pose.controller;

import com.sam.pose.bean.ClubInfo;
import com.sam.pose.dao.ClubInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ClubController {

    @Autowired
    private ClubInfoRepository clubInfoRepository;

    @RequestMapping("/addClub")
    @ResponseBody
    private String add(){
        ClubInfo clubInfo=new ClubInfo("1001","club1001","sitResourceId_1001","1");
        clubInfoRepository.save(clubInfo);
        return "Success";
    }



}
