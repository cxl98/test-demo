package com.cxl;

import com.cxl.server.net.WebSocketServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;

@SpringBootApplication
@Configuration
public class NettyApplication implements CommandLineRunner {
    public static void main(String[] args) {
        SpringApplication.run(NettyApplication.class);
    }
    @Value("${websocket.port}")
    private  int port;

//    public void setPort(int port) {
//        this.port = port;
//    }

    @Autowired
    private WebSocketServer webSocketServer;

    @Override
    public void run(String... args) {
        webSocketServer.run(port);
        Runtime.getRuntime().addShutdownHook(new Thread(() -> webSocketServer.stop()));
    }
}
