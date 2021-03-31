package com.Huang.One;

import org.apache.commons.io.FileUtils;

import javax.imageio.IIOException;
import java.io.File;
import java.io.IOException;
import java.net.URL;

//多线程下载图片
public class TestCreateThread02 extends Thread{
    private String url;
    private String name;

    public TestCreateThread02(String url,String name) {
        this.url = url;
        this.name = name;
    }

    @Override
    public void run() {
        WebDownloader webDownloader = new WebDownloader();
        webDownloader.downloader(url,name);
        System.out.println("下载了文件名为："+name);
    }

    public static void main(String[] args) {
        TestCreateThread02 test = new TestCreateThread02("","02.jpeg");
        test.start();
    }
}
class WebDownloader {

    public WebDownloader() {

    }

    public void downloader(String url, String name){
        try {
            FileUtils.copyURLToFile(new URL(url), new File(name));
        }
        catch(IOException e){
            e.printStackTrace();
            System.out.println("IO异常");
        }
    }
}