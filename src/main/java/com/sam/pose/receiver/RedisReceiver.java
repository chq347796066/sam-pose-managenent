package com.sam.pose.receiver;

import com.sam.pose.util.Global;
import org.springframework.stereotype.Service;





@Service
public class RedisReceiver {
    public void receiveMessage(String message) {
        Global.pushMsg(message);
    }
}
