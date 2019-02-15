package com.sam.pose.util;

import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import io.netty.util.AttributeKey;
import io.netty.util.concurrent.GlobalEventExecutor;

import java.util.concurrent.ConcurrentHashMap;

public class Global {
    public static ChannelGroup group = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);
    public static int httpPort = 0;
    public static ConcurrentHashMap<String,ChannelGroup> users = new ConcurrentHashMap<String,ChannelGroup>();
    public static final AttributeKey<String> CHANNEL_TOKEN_KEY = AttributeKey.valueOf("netty.channel.token");
    //用redis list功能

    public static void pushMsg(String userId,String message) {
        TextWebSocketFrame tws = new TextWebSocketFrame(message);
        if (Global.users.get(userId) != null){
            Global.users.get(userId).writeAndFlush(tws);
        }
    }

    public static void pushMsg(String message){
        TextWebSocketFrame tws = new TextWebSocketFrame(message);
        for(String userId:Global.users.keySet()){
            Global.users.get(userId).writeAndFlush(tws);
        }


    }


}