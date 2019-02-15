package com.sam.pose.controller;

import com.sam.pose.bean.*;
import com.sam.pose.dao.AlertInfoRepository;
import com.sam.pose.dao.StoreInfoRepository;
import com.sam.pose.parameter.SettingBean;
import com.sam.pose.server.CameraSettingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class HomeController {

    @Autowired
    private StoreInfoRepository storeInfoRepository;
    @Autowired
    private AlertInfoRepository alertInfoRepository;

    @Autowired
    private CameraSettingService cameraSettingService;

    private boolean isFirst=true;

    private List<StoreInfo>storeInfos;
    private List<AlertInfo>alertInfos;
    @RequestMapping("/home")
    public String home(Model model){
        System.out.println(isFirst);
        if(isFirst) {
            storeInfos = (List<StoreInfo>) storeInfoRepository.findAll();
            alertInfos = (List<AlertInfo>) alertInfoRepository.findAll();
            isFirst=false;
        }
        model.addAttribute("storeInfos", storeInfos);
        model.addAttribute("alertInfos", alertInfos);
        return "ListPage";
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

    @RequestMapping(value = "/pose",method = RequestMethod.GET)
    public String pose(){
        return "openpose";
    }
}
