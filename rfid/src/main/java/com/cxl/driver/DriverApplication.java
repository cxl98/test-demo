package com.cxl.driver;

import com.cxl.driver.net.DriverServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;

@SpringBootApplication
public class DriverApplication implements CommandLineRunner {
    public static void main(String[] args) {
        SpringApplication.run(DriverApplication.class);
    }
    @Autowired
    private DriverServer driverServer;
    @Override
    public void run(String... args) throws Exception {
        driverServer.start();
//        Runtime.getRuntime().addShutdownHook(new Thread(() -> driverServer.stop()));
    }
}
