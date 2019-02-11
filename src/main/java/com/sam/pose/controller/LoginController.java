package com.sam.pose.controller;

import com.sam.pose.bean.UserInfo;
import com.sam.pose.server.UserService;
import com.sam.pose.util.Md5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;


@Controller
public class LoginController {

    @Autowired
    private UserService userService;



    @RequestMapping(value = "/login",method = RequestMethod.GET)
    public String login(Model model){
        return "login";
    }







    @RequestMapping(value = "/loginUser",method = RequestMethod.POST)
    public String login(String userName,String password,HttpSession session,RedirectAttributes rAttributes){
        UserInfo userInfo=userService.findByUserName(userName);
        if(userInfo==null){
            rAttributes.addFlashAttribute("error","Account does not exist");
            return "redirect:/login";
        }else{
            if(!Md5Util.md5(password).equals(userInfo.getUserPwd())){
                rAttributes.addFlashAttribute("error","Wrong password");
                return "redirect:/login";
            }else {
                session.setAttribute("userName",userName);
                rAttributes.addFlashAttribute("userName",userName);
                return "redirect:/home";
            }
        }
    }


    @RequestMapping(value = "/logout",method = RequestMethod.GET)
    public String logout(HttpSession session){
        System.out.println("logout");
        session.setAttribute("userName",null);
        return "redirect:/login";
    }




}



