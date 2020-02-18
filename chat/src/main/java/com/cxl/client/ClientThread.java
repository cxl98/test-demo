package com.cxl.client;

import java.io.BufferedReader;
import java.io.IOException;

public class ClientThread extends Thread {
    private BufferedReader brServer = null;

    public ClientThread(BufferedReader brServer) {
        this.brServer = brServer;
    }

    @Override
    public void run() {
        try {
            String line = "";
            while ((line = brServer.readLine()) != null) {
                System.out.println(line);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (brServer != null) {

                    brServer.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}

