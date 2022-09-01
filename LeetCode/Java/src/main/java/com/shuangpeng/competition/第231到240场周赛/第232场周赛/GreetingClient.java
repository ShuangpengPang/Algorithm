package com.shuangpeng.competition.第231到240场周赛.第232场周赛;

import java.io.*;
import java.net.Socket;

public class GreetingClient {
    public static void main(String[] args) {
        int count = 0;
        while (true) {
            if (count > 10000) {
                break;
            }
            String serverName = "127.0.0.1";
            int port = 1048;
            try {
                System.out.println("连接到主机：" + serverName + " ，端口号：" + port);
                Socket client = new Socket(serverName, port);
                System.out.println("远程主机地址：" + client.getRemoteSocketAddress());
                OutputStream outToServer = client.getOutputStream();
                DataOutputStream out = new DataOutputStream(outToServer);

                out.writeUTF("Hello from " + client.getLocalSocketAddress());
                InputStream inFromServer = client.getInputStream();
                DataInputStream in = new DataInputStream(inFromServer);
                System.out.println("服务器响应： " + in.readUTF());
                client.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.err.println("连接次数：" + ++count);
        }
    }
}
