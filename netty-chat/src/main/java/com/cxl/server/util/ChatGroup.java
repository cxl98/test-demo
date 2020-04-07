package com.cxl.server.util;

import io.netty.channel.group.ChannelGroup;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class ChatGroup {
    private static ConcurrentMap<String, ChannelGroup> chatGroupMap=new ConcurrentHashMap<>();
    public static void addChatGroup(String name,ChannelGroup channels){
        if (null!=name&&null!=channels){
            if (!chatGroupMap.containsKey(name)){
                if (chatGroupMap.size()>50){
                    chatGroupMap.clear();
                }
                chatGroupMap.put(name,channels);
            }
        }
    }
    public static ChannelGroup getChatGroup(String name){
        return chatGroupMap.get(name);
    }
    public static boolean isExist(String name){
        return chatGroupMap.containsKey(name);
    }
}
