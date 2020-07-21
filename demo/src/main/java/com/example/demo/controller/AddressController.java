package com.example.demo.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.demo.entry.Entry;
import com.example.demo.util.MapUtil;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api")
public class AddressController {
    @RequestMapping(value = "/map",method = RequestMethod.POST,produces="application/json")
    @ResponseBody
    public String xxx(@RequestBody String bog){
        Entry entry = JSON.parseObject(bog, Entry.class);
        double lng = entry.getLng();
        double lat = entry.getLat();
        String address = MapUtil.getAddress(lng, lat);
        entry.setAddress(address);
        return JSON.toJSONString(entry);
    }
}
