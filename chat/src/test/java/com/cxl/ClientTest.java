package com.cxl;

import com.cxl.client.Client;

public class ClientTest {
    public static void main(String[] args) {
        Client client=new Client();
        client.init();
        client.readAndSend();
    }
}
