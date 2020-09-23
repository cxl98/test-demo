package com.cxl.order.controller;

import com.cxl.order.entry.Producer;
import com.cxl.order.service.ProducerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ProducerController {
    @Autowired
    private ProducerService producerService;

    @RequestMapping(value = "addProducer",method = RequestMethod.POST)
    @ResponseBody
    public int addProducer(Producer producer){
        if (null!=producer){
            return producerService.addProducer(producer);
        }
        return 0;
    }
}
