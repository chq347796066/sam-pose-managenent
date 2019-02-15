package com.sam.pose.listener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

@Component
public class RedisListener implements MessageListener {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Override
    public void onMessage(Message message, byte[] bytes) {
        System.out.println("收到消息");
        byte[]body=message.getBody();
        byte[] channel = message.getChannel();
        String topic = (String)stringRedisTemplate.getValueSerializer().deserialize(channel);
        if(topic.equals("publish_info")){
            String msg = (String)stringRedisTemplate.getValueSerializer().deserialize(body);
            System.out.println("订阅我消息:"+msg);


        }



    }
}
