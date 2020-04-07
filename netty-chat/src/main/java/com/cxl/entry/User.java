package com.cxl.entry;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private String name;
    private String channelId;


    @Override
    public String toString() {
        return "{" +
                "name:'" + name + '\'' +
                ", channelId:'" + channelId + '\'' +
                '}';
    }
}
