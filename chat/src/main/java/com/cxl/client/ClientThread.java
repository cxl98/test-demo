package com.cxl.client;

import java.io.BufferedReader;
import java.io.IOException;

public class ClientThread extends Thread {
    private BufferedReader brServer;
    public ClientThread(BufferedReader brServer) {
        this.brServer=brServer;
    }

    @Override
    public void run() {
        try {
            String line="";
            while((line=brServer.readLine())!=null){
                System.out.println(line);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            if (brServer != null) {
                try {
                    brServer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
