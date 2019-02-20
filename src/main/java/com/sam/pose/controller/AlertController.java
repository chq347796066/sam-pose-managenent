package com.sam.pose.controller;

import com.sam.pose.bean.AlertInfo;
import com.sam.pose.dao.AlertInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class AlertController {

    @Autowired
    public AlertInfoRepository alertInfoRepository;

    @RequestMapping(value = "/findAllAlert",method = RequestMethod.POST)
    @ResponseBody
    public List<AlertInfo>findAllAlert(){
        Iterable<AlertInfo> alertInfos = alertInfoRepository.findAll();
        return (List<AlertInfo>)alertInfos;
    }
}
