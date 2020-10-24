package com.cxl.order.controller;

import com.cxl.order.annotation.Log;
import com.cxl.order.entry.Producer;
import com.cxl.order.service.ProducerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@Controller
public class ProducerController {
    @Autowired
    private ProducerService producerService;

    @PostMapping(value = "addProducer",produces="application/json")
    @ResponseBody
    @Transactional
    @Log(operation = "login_api",type = "test")
    public int addProducer(@RequestBody Producer producer){
        if (null!=producer){
            return producerService.addProducer(producer);
        }
        return 0;
    }
}
