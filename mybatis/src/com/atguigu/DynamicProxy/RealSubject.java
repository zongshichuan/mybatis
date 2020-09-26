package com.atguigu.DynamicProxy;

/**
 * 需要代理的实际对象
 */
public class RealSubject  implements Subject{
    @Override
    public String SayHello(String name) {
        return "hello" + name;
    }

    @Override
    public String SayGoodBey() {
        return "good bye";
    }
}
