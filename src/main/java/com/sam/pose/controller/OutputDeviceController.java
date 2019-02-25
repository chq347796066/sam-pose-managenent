package com.sam.pose.controller;

import com.sam.pose.bean.ClubInfo;
import com.sam.pose.bean.OutputDeviceInfo;
import com.sam.pose.bean.Result;
import com.sam.pose.dao.ClubInfoRepository;
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
    @Autowired
    private ClubInfoRepository clubInfoRepository;

    @RequestMapping(value = "/addOutputDevice",method = RequestMethod.POST)
    @ResponseBody
    public Result addOutputDevice(String name,String clubId){
        System.out.println("name:"+name+"clubId:"+clubId);
        try {
            List<ClubInfo>clubInfos=clubInfoRepository.findByClubId(clubId);
            if(clubInfos!=null&&clubInfos.size()>0) {
                List<OutputDeviceInfo> devices = outputDeviceRepository.findByName(name);
                if (devices != null && devices.size() > 0) {
                    return new Result("1", "Have the same name, please re-enter");
                }
                String deviceId=clubId+"_"+name;
                OutputDeviceInfo info = new OutputDeviceInfo(deviceId, name, clubId, clubId);
                outputDeviceRepository.save(info);
                return new Result("0", "Added successfully");
            }else {
                return new Result("1","club does not exist");
            }
        }catch (Exception e){
            System.out.println(e);
            return new Result("1","Network error");
        }

    }

    @RequestMapping("/findAllOutput")
    @ResponseBody
    public List<OutputDeviceInfo>findAll(){
        return (List<OutputDeviceInfo>) outputDeviceRepository.findAll();

    }

    @RequestMapping("/findOutputByClubId")
    @ResponseBody
    public List<OutputDeviceInfo>findOutputByClubId(String clubId){
        return outputDeviceRepository.findByClubId(clubId);

    }
    @RequestMapping("/deleteAllOutput")
    @ResponseBody
    public String delete(){
        outputDeviceRepository.deleteAll();
        return "success";

    }
    @RequestMapping("/deleteOutput")
    @ResponseBody
    public Result deleteOutput(String deviceId){
        System.out.println("deviceId:"+deviceId);
        try {
            List<OutputDeviceInfo>outputDeviceInfos=outputDeviceRepository.findByDeviceId(deviceId);
            if(outputDeviceInfos!=null&&outputDeviceInfos.size()>0){
                outputDeviceRepository.delete(outputDeviceInfos.get(0));
                return new Result("0", "Delete successfully");
            }
            return new Result("1","Delete failed");
        }catch (Exception e){
            System.out.println(e.getMessage());
            return new Result("1","Network error");
        }

    }

}
