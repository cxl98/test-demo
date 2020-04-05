package com.cxl.server;

public class TestServer {
    public static void main(String[] args) {
        Server server=new Server(8888);
        server.init();
    }
}
