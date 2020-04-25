package com.cxl.test.comtroller.config;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @RequestMapping("/xxx")
    public String xxx(){
        return "xxx";
    }
}
