package com.cxl.count.controller;

import com.alibaba.fastjson.JSON;
import com.cxl.count.entry.Entry;
import com.cxl.count.util.PoiUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/api")
public class CountController {
    private Entry entry=new Entry();
    @RequestMapping(value = "/count",method = RequestMethod.POST,produces="application/json")
    @ResponseBody
    public String xxx(@RequestBody String bog){
        System.out.println(bog);
        entry = JSON.parseObject(bog, Entry.class);
        System.out.println(entry);
        float meters = entry.getMeters();//米数
        float malevolent = entry.getMalevolent();//单价
        float weight = entry.getWeight();//重量
        int number = entry.getNumber();//数量
        int machining = entry.getMachining();//加工
        float totalWeight=meters*number*weight;
        float machSum=(machining/1000)*totalWeight;
        float sum=totalWeight*malevolent;
        float total=sum+machSum;
        int freight = entry.getFreight();//运费
        float num=total+freight;
        entry.setTotalWeight(totalWeight);
        entry.setTotalPrice(num);

        return JSON.toJSONString(entry);
    }

    @RequestMapping(value = "/export",method = RequestMethod.GET)
    public ResponseEntity<byte[]> export(){
        List<Entry> entries=new ArrayList<>();
        entries.add(entry);
        return PoiUtils.exportExcel(entries);
    }
}
