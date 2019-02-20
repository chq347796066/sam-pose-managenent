package com.sam.pose.controller;

import com.sam.pose.bean.OutputDeviceInfo;
import com.sam.pose.bean.Result;
import com.sam.pose.dao.OutputDeviceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class OutputDeviceController {

    @Autowired
    private OutputDeviceRepository outputDeviceRepository;

    @RequestMapping(value = "/addOutputDevice",method = RequestMethod.POST)
    @ResponseBody
    public Result addOutputDevice(String name){
        System.out.println("name:"+name);
        try {
            List<OutputDeviceInfo>devices=outputDeviceRepository.findByName(name);
            if(devices!=null&&devices.size()>0){
                return new Result("1","Have the same name, please re-enter");
            }
            OutputDeviceInfo info = new OutputDeviceInfo(null, name, "1");
            outputDeviceRepository.save(info);
            return new Result("0", "Added successfully");
        }catch (Exception e){
            System.out.println(e);
            return new Result("1","Network error");
        }

    }
    @RequestMapping("/addDevice")
    @ResponseBody
    public String addOutputDevice(){
        List<OutputDeviceInfo>infos=outputDeviceRepository.findByName("6372_111");
        OutputDeviceInfo info=new OutputDeviceInfo("1","6372_111","1");
        outputDeviceRepository.save(info);
        return "success";

    }
}
