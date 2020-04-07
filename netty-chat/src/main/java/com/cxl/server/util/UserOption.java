package com.cxl.server.util;

import com.cxl.entry.Params;
import com.cxl.entry.User;
import io.netty.channel.Channel;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;

import java.util.ArrayList;
import java.util.List;

public class UserOption {
    private static List<User> users=new ArrayList<>();

    public static void addUser(User user){
        User search=search(user.getName());
        if (null!=search){
           Channel channel= ChannelGroupUtil.findChannel(search.getChannelId());
            try {
                channel.disconnect().sync();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        users.add(user);
        Params params=new Params(Params.ONLINE,user);
        TextWebSocketFrame textWebSocketFrame=new TextWebSocketFrame(params.toString());
        ChannelGroupUtil.sendAll(textWebSocketFrame);
    }
    public static void removeUser(Channel channel){
        User search=search(channel);
        users.remove(search);
        Params params=new Params(Params.OFFLINE,search);
        TextWebSocketFrame textWebSocketFrame=new TextWebSocketFrame(params.toString());
        ChannelGroupUtil.sendAll(textWebSocketFrame);
    }

    private static User search(Channel channel) {
        User target=new User();
        target.setChannelId(channel.id().toString());
        for (User user: users) {
            if (user.equals(target)){
                return user;
            }
        }
        return target;
    }

    private static User search(String name) {
        for (User user: users) {
            if (user.getName().equals(name)){
                return user;
            }
        }
        return null;
    }

    public static List<User> getUsers() {
        return users;
    }
}
