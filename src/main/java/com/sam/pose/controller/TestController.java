package com.sam.pose.controller;

import com.sam.pose.bean.AlertInfo;
import com.sam.pose.bean.CameraInfo;
import com.sam.pose.bean.ClubInfo;
import com.sam.pose.server.AlertService;
import com.sam.pose.server.CameraService;
import com.sam.pose.server.StoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class TestController {

    @Autowired
    private StoreService storeService;

    @Autowired
    private CameraService cameraService;
    @Autowired
    private AlertService alertService;

    @RequestMapping("/test")
    @ResponseBody
    public String  test(){
        findAllAlert();
        return "test";
    }


    public Iterable<ClubInfo> findAllStore(){
        Iterable<ClubInfo>storeInfos=storeService.findAll();
        return storeInfos;
    }





    public Iterable<CameraInfo> findAllCamera(){
        Iterable<CameraInfo>cameraInfos=cameraService.findAll();
        cameraInfos.forEach(t-> System.out.println(t));
        return cameraInfos;
    }


    public void findAllAlert(){
        Iterable<AlertInfo>iterable=alertService.findAll();
        iterable.forEach(t-> System.out.println(t));
    }







}
