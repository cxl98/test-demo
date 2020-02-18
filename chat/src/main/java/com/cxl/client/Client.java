package com.cxl.client;

import com.cxl.common.Protocol;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;

public class Client {
    private static final int SERVER_PORT = 9999;
    private String address = "47.93.225.242";
    private Socket socket;
    private PrintStream printStream;
    private BufferedReader brServer;
    private BufferedReader key;

    public void init() {

        try {
            key = new BufferedReader(new InputStreamReader(System.in));
            socket = new Socket(address, SERVER_PORT);
            printStream = new PrintStream(socket.getOutputStream());
            brServer = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String line = "";
            while (true) {
                String userName = JOptionPane.showInputDialog(line + "输入用户名");
                printStream.println(Protocol.USER_ROND + userName + Protocol.USER_ROND);

                String result = brServer.readLine();
                if (result.equals(Protocol.NAME_EER)) {
                    line = "用户名重复,请重新输入";
                    continue;
                }
                if (result.equals(Protocol.LOGIN_SUCCESS)) {
                    System.out.println("登录成功" + userName);
                    break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        new ClientThread(brServer).start();
    }

    public void readAndSend() {
        try {
            String line;
            while ((line = key.readLine()) != null) {
                //如果发送的消息中带有冒号,且以@开头,则为私聊信息
                if (line.indexOf(":") > 0 && line.startsWith("@")) {
                    line = line.substring(1);
                    String[] lines = line.split(":");
                    printStream.println(Protocol.PRIVATE_ROND + lines[0] + Protocol.SPLIT_SIGN + lines[1] + Protocol.PRIVATE_ROND);
                } else if (line.startsWith("@@")) {
                    line = line.substring(2);
                    printStream.println(Protocol.MSG_ROND + line+ Protocol.SPLIT_SIGN + Protocol.MSG_ROND);
                }else if ("exit".equals(line)){
                    printStream.println("exit");
                    System.exit(0);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (key != null) {
                    key.close();
                }
                if (brServer != null) {
                    brServer.close();
                }
                if (socket != null) {
                    socket.close();
                }
                if (printStream!=null){
                    printStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    public static void main(String[] args) {
        Client client=new Client();
        client.init();
        client.readAndSend();
    }
}
