package com.Huang.One;

public class StaticProxyDemo {

    public static void main(String[] args) {
    //静态代理模式，代理对象，实际对象实现同一个借口，如果实现的是runnable接口呢
        //就相当于thread类和自己写的实现runnable接口的关系，thread类帮你静态代理了
        Company company = new Company(new You());
        company.HappyMarry();

    }

}
interface Marry{
    void HappyMarry();
}
class You implements Marry{
    @Override
    public void HappyMarry() {
        System.out.println("happy husband");
    }
}
class Company implements Marry{
    private Marry target;

    public Company(Marry target) {
        this.target = target;
    }
    @Override
    public void HappyMarry() {
        before();
        target.HappyMarry();
        after();

    }
    private void before(){
        System.out.println("before");
    }
    private void after(){
        System.out.println("after");
    }
}