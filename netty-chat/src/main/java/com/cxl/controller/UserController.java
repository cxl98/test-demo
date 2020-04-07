package com.cxl.controller;

import com.cxl.entry.ChatWithGroup;
import com.cxl.entry.Params;
import com.cxl.entry.User;
import com.cxl.server.util.ChannelGroupUtil;
import com.cxl.server.util.ChatGroup;
import com.cxl.server.util.UserOption;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import io.netty.util.concurrent.GlobalEventExecutor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
@CrossOrigin
@Controller
public class UserController {
    @RequestMapping("/")
    public String login() {
        return "login";
    }

    @RequestMapping("/doLogin")
    public String doLogin(String name, Map<String, Object> paramMap) {
        paramMap.put("name", name);
        return "chatPage";
    }

    @RequestMapping("/getOnlineUser")
    @ResponseBody
    public List<User> getOnlineUser() {
        for (User user:
                UserOption.getUsers()) {
            System.out.println(user);
        }
        return UserOption.getUsers();
    }

    @RequestMapping("/createGroups")
    @ResponseBody
    public Map createGroups(@RequestBody ChatWithGroup chat) {
        Map<String, Object> map = new HashMap<>();
        if (ChatGroup.isExist(chat.getGroupName())) {
            map.put("code", "300");
            map.put("data", "群聊名字已经存在!!");
            return map;
        } else {
            List<String> members=chat.getGroupMembers();
            ChannelGroup group=new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);
            for (String member:members) {
                group.add(ChannelGroupUtil.findChannel(member));
            }
            //广播邀请信息
            members.clear();
            Params params=new Params(Params.GROUP_APPLY,chat.toString());
            TextWebSocketFrame textWebSocketFrame=new TextWebSocketFrame(params.toString());

            //发送信息
            group.writeAndFlush(textWebSocketFrame);
            //清空
            group.clear();
            group.add(ChannelGroupUtil.findChannel(chat.getCreateGroup()));
            //记录创建者
            members.add(chat.getCreateGroup());
            ChatGroup.addChatGroup(chat.getGroupName(),group);
            map.put("code","200");
            map.put("data",chat);
            return map;
        }
    }

    @RequestMapping("/joinGroups")
    @ResponseBody
    public Map joinGroups(String channelId,String groupName){
        Map<String,Object> map=new HashMap<>();
        if (ChatGroup.isExist(groupName)){
            map.put("code","200");
            ChannelGroup channels=ChatGroup.getChatGroup(groupName);
            channels.add(ChannelGroupUtil.findChannel(channelId));
            map.put("data","成功加入");
        }else{
            map.put("code","300");
            map.put("data","邀请已失效");
        }
        return map;
    }
}
