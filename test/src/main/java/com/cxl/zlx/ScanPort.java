package com.cxl.zlx;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.LinkedList;

public class ScanPort {
    public static void portScan(String ip, int startPort, int endPort) {
        if (startPort > endPort) {
            System.out.println("输入参数错误");
            return;
        }

        LinkedList<Thread> threadPool = new LinkedList<>();
        for (int i = startPort; i < endPort; i++) {
            int port = i;
            Socket socket = new Socket();
            Runnable run = new Runnable() {
                @Override
                public void run() {
                    try {
                        socket.connect(new InetSocketAddress(ip, port));
                        System.out.println("端口" + port + ":开放");
                    } catch (IOException e) {
/*                            if (port == 8888)
                                e.printStackTrace();
                            logger.Info("端口"+port+":关闭");*/
                    }
                }
            };
            Thread th = new Thread(run);
            th.start();
            threadPool.add(th);
        }
    }

    public static void main(String[] args) {
        portScan("39.97.209.17",0,1024);
    }
}
