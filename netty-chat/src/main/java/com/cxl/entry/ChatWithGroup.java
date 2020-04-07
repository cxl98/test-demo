package com.cxl.entry;

import lombok.Data;

import java.util.List;
@Data
public class ChatWithGroup {
    private String createGroup;
    private String groupName;
    private String channelId;
    private List<String> groupMembers;

    @Override
    public String toString() {
        return "{" +
                "creator:'" + createGroup + '\'' +
                ", groupName:'" + groupName + '\'' +
                ", channelId:'" + channelId + '\'' +
                ", members:" + groupMembers +
                '}';
    }
}
