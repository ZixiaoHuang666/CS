package com.huang.net1;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class TestTcpS {
    //要有一个地址
    public static void main(String[] args) {
        ServerSocket sc = null;
        Socket accept = null;
        InputStream is = null;
        ByteArrayOutputStream baos = null;
        try {
            sc = new ServerSocket(9999);
            while(true) {
                //等待客户端连接
                accept = sc.accept();
                //读取客户端消息
                is = accept.getInputStream();
                baos = new ByteArrayOutputStream();
                byte[] bytes = new byte[1024];
                int len;
                while ((len = is.read(bytes)) != -1) {
                    baos.write(bytes, 0, len);

                }
                System.out.println(baos.toString());
            }

        }
        catch(IOException e){
            e.printStackTrace();
        }
        finally{
            if(baos != null) {
                try {
                    baos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(accept!=null) {
                try {
                    accept.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(sc!=null) {
                try {
                    sc.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }


}
