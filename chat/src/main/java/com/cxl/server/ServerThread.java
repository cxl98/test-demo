package com.cxl.server;

import com.cxl.common.Protocol;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;

public class ServerThread extends Thread {
    private Socket socket;
    private PrintStream printStream = null;
    private BufferedReader brClient = null;

    public ServerThread(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            brClient = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            //一个客户端对应一个输出流对象
            printStream = new PrintStream(socket.getOutputStream());
            String line = "";
            while ((line = brClient.readLine()) != null) {
                if (line.startsWith(Protocol.USER_ROND) && line.endsWith(Protocol.USER_ROND)) {
                    String userName = getRealMsg(line);
                    if (Server.chatMap.map.containsKey(userName)) {
                        printStream.println(Protocol.NAME_EER);
                    }else{
                        System.out.println(""+userName+" 登录成功，你可以开始聊天了！");
                        printStream.println(Protocol.LOGIN_SUCCESS);
                        //将用户名和输出流对象组成的键值关联对存入前面经过改造的map
                        Server.chatMap.map.put(userName,printStream);
                    }
                }else if (line.startsWith(Protocol.PRIVATE_ROND)&&line.endsWith(Protocol.PRIVATE_ROND)){
                    String userAndMsg=getRealMsg(line);
                    String [] msg=userAndMsg.split(Protocol.SPLIT_SIGN);
                    Server.chatMap.map.get(msg[0]).println("私聊信息来自"+Server.chatMap.getKeyByValue(printStream)+" : "+msg[1]);
                }else if (line.startsWith(Protocol.MSG_ROND)&&line.startsWith(Protocol.MSG_ROND)){
                    String msg=getRealMsg(line);
                    String [] msgs=msg.split(Protocol.SPLIT_SIGN);

                    for (PrintStream ps: Server.chatMap.valueSet()) {
                        ps.println("群聊信息 来自"+Server.chatMap.getKeyByValue(printStream)+" : "+msgs[0]);
                    }
                }else if ("exit".equals(line)){
                    Server.chatMap.removeByValue(printStream);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String getRealMsg(String line) {
        return line.substring(Protocol.PROTOCOL_LEN, line.length() - Protocol.PROTOCOL_LEN);
    }
}
