package com.sam.pose.controller;

import com.sam.pose.bean.AlertInfo;
import com.sam.pose.bean.CameraInfo;
import com.sam.pose.bean.StoreInfo;
import com.sam.pose.server.AlertService;
import com.sam.pose.server.CameraService;
import com.sam.pose.server.StoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Iterator;

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
        createAlert(2);
        findAllAlert();
        return "test";
    }

    public void createStore(int size){
        for(int i=0;i<size;i++){
            StoreInfo storeInfo=new StoreInfo("store_"+i,"storename_"+i,"sitResourceId_"+i,"1");
            storeService.save(storeInfo);
            System.out.println("add store success");
        }
    }

    public Iterable<StoreInfo> findAllStore(){
        Iterable<StoreInfo>storeInfos=storeService.findAll();
        return storeInfos;
    }



    public void createCamera(int size){
        Iterable<StoreInfo>storeInfos=findAllStore();
        System.out.println(storeInfos);
        Iterator<StoreInfo> iterator = storeInfos.iterator();
        while (iterator.hasNext()){
            String storeId=iterator.next().getStoreId();
            for(int i=0;i<size;i++){
                CameraInfo cameraInfo=new CameraInfo(storeId+"_"+i,storeId,storeId);
                System.out.println(cameraInfo.getCameraId());
                cameraService.save(cameraInfo);
                System.out.println("add camera success");
            }
        }
    }

    public Iterable<CameraInfo> findAllCamera(){
        Iterable<CameraInfo>cameraInfos=cameraService.findAll();
        cameraInfos.forEach(t-> System.out.println(t));
        return cameraInfos;
    }

    public void createAlert(int size){
        Iterable<CameraInfo>cameraInfos=findAllCamera();
        cameraInfos.forEach(t->{
            String cameraId=t.getCameraId();
            for(int i=0;i<size;i++){

                AlertInfo alertInfo=new AlertInfo(cameraId+"_"+i,cameraId,cameraId,t.getStoreId(),"http://www.pptok.com/wp-content/uploads/2012/08/xunguang-4.jpg","2019-01-28");
                alertService.save(alertInfo);
                System.out.println("add alert success");
            }
        });
    }

    public void findAllAlert(){
        Iterable<AlertInfo>iterable=alertService.findAll();
        iterable.forEach(t-> System.out.println(t));
    }







}
