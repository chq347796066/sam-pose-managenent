package com.sam.pose.controller;

import com.sam.pose.bean.CameraInfo;
import com.sam.pose.bean.Result;
import com.sam.pose.bean.ClubInfo;
import com.sam.pose.dao.CameraInfoRepository;
import com.sam.pose.dao.ClubInfoRepository;
import com.sam.pose.server.CameraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class CameraController {

    @Autowired
    private ClubInfoRepository storeInfoRepository;


    @Autowired
    private CameraInfoRepository cameraInfoRepository;


    @Autowired
    private CameraService cameraService;

    @RequestMapping("/addSimpleCam")
    @ResponseBody
    public Result addSimpleCam(String clubId,String resourceId,String camName){
        try{
            System.out.println("club:"+clubId+",resourceId:"+resourceId+",camName:"+camName);
            List<ClubInfo>storeInfos=storeInfoRepository.findByClubId(clubId);
            if(storeInfos.size()==0){
                System.out.println("not exist");
                return new Result("1","Store does not exist");
            }
            String idName=clubId+"_"+resourceId;
            CameraInfo cameraInfo=new CameraInfo(idName,clubId,clubId,null,null,null,null,camName,resourceId,idName,"0");
            cameraInfoRepository.save(cameraInfo);
            System.out.println("success");
            return new Result("0","Added successfully");
        }catch (Exception e){
            System.out.println(e);
            return new Result("1","Network error");
        }
    }


    @RequestMapping("/editSimpleCam")
    @ResponseBody
    public Result editSimpleCam(String cameraId,String cameraName){
        try {
            List<CameraInfo> cameraInfos = cameraInfoRepository.findByCameraId(cameraId);
            if (cameraInfos != null && cameraInfos.size() > 0) {
                CameraInfo cameraInfo = cameraInfos.get(0);
                cameraInfo.setCameraName(cameraName);
                cameraInfoRepository.save(cameraInfo);
                return new Result("0", "Successfully modified");
            } else {
                return new Result("1", "Camera does not exist");
            }
        }catch (Exception e){
            return new Result("1","Network error");
        }

    }

    @RequestMapping("/deleteAllCam")
    @ResponseBody
    public String deleteAll(){
        cameraInfoRepository.deleteAll();
        return "success";
    }
    @RequestMapping("/findAll")
    @ResponseBody
    public Iterable<CameraInfo> findAll(){
        Iterable<CameraInfo>cameraInfos=cameraInfoRepository.findAll();
        cameraInfos.forEach(t-> System.out.println(t));
        return cameraInfos;
    }
    @RequestMapping("/findNmCamera")
    @ResponseBody
    public List<CameraInfo> findNmCamera(){
        List<CameraInfo>cameraInfos=cameraInfoRepository.findByIsMonitor("0");
        cameraInfos.forEach(t-> System.out.println(t));
        return cameraInfos;
    }

    @RequestMapping("/addCamera")
    @ResponseBody
    public String addCamera(){
        CameraInfo cameraInfo=new CameraInfo("1001_06","1001","1001","0.8","15",
                new Integer[]{172,100,96},
                new Integer[]{172,100,96},
                "camera_1001_06",
                "06",
                "1001_06",
                "1");
        cameraInfoRepository.save(cameraInfo);
        return "add success";

    }

    @RequestMapping("/findMCamera")
    @ResponseBody
    public List<CameraInfo>findMCam(){
        List<CameraInfo>cameraInfos=cameraInfoRepository.findByIsMonitor("1");
        return cameraInfos;

    }

    @RequestMapping("/changeMonitorCam")
    @ResponseBody
    public Result changeMonitorCam(String[] monitor){
        try {
            cameraService.changeMonitorCam(monitor);
            return new Result("0", "Successfully modified");
        }catch (Exception e){
            return new Result("1","Network error");
        }

    }

    @RequestMapping("/deleteMonitorCam")
    @ResponseBody
    public Result deleteMonitorCam(String cameraId){
        System.out.println("cameraId:"+cameraId);
        try {
            cameraService.deleteMonitorCam(cameraId);
            return new Result("0", "Successfully modified");
        }catch (Exception e){
            return new Result("1","Network error");
        }

    }




}
