package com.huang.net1;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

public class TestTcpC {
    //要知道服务器地址

    public static void main(String[] args) {
        Socket socket = null;
        OutputStream os = null;
        try {
            InetAddress ServerIp = InetAddress.getByName("127.0.0.1");
            //要知道端口号
            int port = 9999;
            //创建socket连接
            socket = new Socket(ServerIp,port);
            //发送信息io流
            os = socket.getOutputStream();
            os.write("黄子潇".getBytes());



        } catch(IOException e){
            e.printStackTrace();
        }
        finally {
            if(os != null){
                try {
                    os.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(socket != null){
                try {
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }
}

