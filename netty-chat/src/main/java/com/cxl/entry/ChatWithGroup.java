package com.cxl.entry;

import lombok.Data;
import lombok.ToString;

import java.util.List;
@Data
@ToString
public class ChatWithGroup {
    private String createGroup;
    private String groupName;
    private String channelId;
    private List<String> groupMembers;

}
