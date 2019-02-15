package com.sam.pose.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Controller
public class RedisController {


    @Autowired
    private StringRedisTemplate redisTemplate;

    private static final String KEY="redis_pics_key";

    @RequestMapping("/read")
    @ResponseBody
    public List<String> read(){
        Set<String> pic_set=redisTemplate.opsForSet().members(KEY);
        List<String> list=new ArrayList<>();
        for (String value:pic_set) {
            list.add("/image/"+value);
        }
        return list;

    }

    @RequestMapping("/readAll")
    @ResponseBody
    public List<String> readAllKey(){
        List<String> list=redisTemplate.opsForList().range("ws_data_list", 0 ,-1);
        System.out.println(list.size());
        return list;

    }

}
