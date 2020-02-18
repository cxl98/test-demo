package com.cxl.server;

import com.cxl.common.ChatMap;

import java.io.IOException;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    private ServerSocket serverSocket = null;
    private final int SERVER_PORT = 9999;
    private Socket socket=null;

    public static ChatMap<String, PrintStream> chatMap = new ChatMap<String, PrintStream>();

    public void init() {
        try {
            serverSocket = new ServerSocket(SERVER_PORT);
            while (true) {
                 socket = serverSocket.accept();
                new ServerThread(socket).start();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void main(String[] args) {
        Server server=new Server();
        server.init();
    }
}
