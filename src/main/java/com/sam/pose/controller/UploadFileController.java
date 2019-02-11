package com.sam.pose.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@Controller
public class UploadFileController {


    @RequestMapping(value = "/upload")
    public String upload(){
        return "upload";
    }


    @RequestMapping(value = "/upload",method = RequestMethod.POST)
    public  void upload(HttpServletRequest request,
                          @RequestParam("description") String description,
                          @RequestParam("file")MultipartFile file) throws IOException {
        if (!file.isEmpty()){
            String contentType = file.getContentType();
            String fileName = file.getOriginalFilename();
            String filePath = request.getSession().getServletContext().getRealPath("imgupload/");
            System.out.println("contentType:"+contentType);
            System.out.println("fileName:"+fileName);
            System.out.println("filePath:"+filePath);
            System.out.println(description);
        }
    }

}
