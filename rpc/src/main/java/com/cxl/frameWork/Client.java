package com.cxl.frameWork;


public class Client {

    public static void main(String[] args) throws NoSuchMethodException {
//        HelloService helloService = new HelloServiceImpl();
        HelloService helloService = ProxyFrameWork.refer(HelloService.class, "localhost", 6666);
        helloService.hello("hello");
    }
}
