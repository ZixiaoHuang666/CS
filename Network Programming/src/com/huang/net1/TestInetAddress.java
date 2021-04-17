package com.huang.net1;

import java.net.InetAddress;
import java.net.UnknownHostException;

//这个类没有构造器只能静态方法返回自己的对象
public class TestInetAddress {
    public static void main(String[] args) throws UnknownHostException {
        InetAddress ip = InetAddress.getByName("127.0.0.1");
        System.out.println(ip);
        InetAddress ip3 = InetAddress.getByName("localhost");
        InetAddress ip4 = InetAddress.getLocalHost();
        InetAddress ip2 = InetAddress.getByName("www.baidu.com");
        System.out.println(ip2);
        System.out.println(ip3);
        System.out.println(ip4);

    }
}
