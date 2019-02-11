package com.sam.pose.controller;

import com.google.gson.Gson;
import com.sam.pose.bean.*;
import com.sam.pose.dao.AlertInfoRepository;
import com.sam.pose.dao.StoreInfoRepository;
import com.sam.pose.parameter.SettingBean;
import com.sam.pose.server.CameraSettingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
public class HomeController {

    @Autowired
    private StoreInfoRepository storeInfoRepository;
    @Autowired
    private AlertInfoRepository alertInfoRepository;

    @Autowired
    private CameraSettingService cameraSettingService;

    @RequestMapping("/home")
    public String home(Model model){
        List<StoreInfo>storeInfos=(List<StoreInfo>) storeInfoRepository.findAll();
        List<AlertInfo>alertInfos=(List<AlertInfo>) alertInfoRepository.findAll();
        model.addAttribute("storeInfos",storeInfos);
        model.addAttribute("alertInfos",alertInfos);
        return "ListPage";
    }

    @RequestMapping(value = "/settingTest")
    @ResponseBody
    public String settingTest(){
        //CameraSetting(settingId=1, cameraId=111, partitionId=store_6372_111, compressionRatio=0.3, vedioFps=10, colorMin=null, colorMax=null, storeId=6372)
        List<Integer>colorMin=new ArrayList<>();
        List<Integer>colorMax=new ArrayList<>();
        colorMin.add(92);
        colorMin.add(79);
        colorMin.add(25);
        colorMax.add(140);
        colorMax.add(255);
        colorMax.add(255);

        CameraSetting cameraSetting=new CameraSetting("1","111","store_6372_111","0.3","10",colorMin,colorMax,"6372");
        cameraSettingService.save(cameraSetting);
        Iterable<CameraSetting>cameraSettings=cameraSettingService.findAll();
        cameraSettings.forEach(t-> System.out.println(t));
        return "success";
    }
    @RequestMapping(value = "/setting",method = RequestMethod.POST)
    @ResponseBody
    public Result setting(SettingBean settingBean){
        System.out.println(settingBean);
        String cameraId=settingBean.getCameraId();
        CameraInfo cameraInfo=cameraSettingService.findByCameraId(cameraId);
        if(cameraInfo==null){
            System.out.println("cameraInfo null");
            return new Result("1","The camera you entered does not exist, please re-enter");
        }else {
            CameraSetting cameraSetting = new CameraSetting(cameraId, cameraId, "1",
                    settingBean.getCompressionRatio(), settingBean.getVedioFps(),
                    settingBean.getColorMin(),settingBean.getColorMax(), "test_store");
            cameraSettingService.save(cameraSetting);
            return new Result("0","Setting success");
        }
    }
}
