package com.sam.pose.controller;

import com.sam.pose.bean.AlertInfo;
import com.sam.pose.dao.AlertInfoRepository;
import com.sam.pose.util.TimeUtil;
import com.sun.org.apache.regexp.internal.RE;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.concurrent.TimeUnit;

@Controller
public class AlertController {

    @Autowired
    public AlertInfoRepository alertInfoRepository;

    @RequestMapping(value = "/findAllAlert",method = RequestMethod.POST)
    @ResponseBody
    public List<AlertInfo>findAllAlert(String clubId){
        System.out.println("clubId:"+clubId);
        if(clubId!=null&&!clubId.equals("")) {
            return alertInfoRepository.findByClubId(clubId);
        }else {
            Iterable<AlertInfo> alertInfos = alertInfoRepository.findAll();
            return (List<AlertInfo>) alertInfos;
        }
    }
    @RequestMapping(value = "/findAlertByKey",method = RequestMethod.POST)
    @ResponseBody
    public AlertInfo findAlertByKey(String key){
        System.out.println("key:"+key);
        try {
            key = "68d00fcc-334f-11e9-b8ab-186024a7a56b";
            List<AlertInfo> alertInfos = alertInfoRepository.findByAlertId(key);
            for (int i = 0; i < 3; i++) {
                if (alertInfos != null && alertInfos.size() > 0) {
                    break;
                } else {
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    alertInfos = alertInfoRepository.findByAlertId(key);
                }
            }
            AlertInfo alertInfo=alertInfos.get(0);
            alertInfo.setTime(TimeUtil.stampToDate(alertInfo.getTime()));
            return alertInfos.get(0);
        }catch (Exception e){
            return null;
        }
    }

    @RequestMapping("/findAllAlert")
    @ResponseBody
    public Iterable<AlertInfo>findAll(){
        return alertInfoRepository.findAll();
    }
}
