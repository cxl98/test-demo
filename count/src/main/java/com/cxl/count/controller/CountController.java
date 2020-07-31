package com.cxl.count.controller;

import com.alibaba.fastjson.JSON;
import com.cxl.count.entry.Entry;
import com.cxl.count.entry.Total;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api")
public class CountController {
    @RequestMapping(value = "/count",method = RequestMethod.POST,produces="application/json")
    @ResponseBody
    public String xxx(@RequestBody String bog){
        System.out.println(bog);
        Entry entry = JSON.parseObject(bog, Entry.class);
        System.out.println(entry);
        int meters = entry.getMeters();//米数
        int malevolent = entry.getMalevolent();//单价
        int weight = entry.getWeight();//重量
        int number = entry.getNumber();//数量
        int machining = entry.getMachining();//加工
        int totalWeight=meters*number*weight;
        int sum=totalWeight*malevolent;
        int freight = entry.getFreight();//运费
        Total total=new Total();
        total.setTotalWeight(totalWeight);
        total.setTotalPrice(sum);

        return JSON.toJSONString(total);
    }
}
