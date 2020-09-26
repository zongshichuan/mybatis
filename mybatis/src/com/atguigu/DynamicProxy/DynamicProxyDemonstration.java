package com.atguigu.DynamicProxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

/**
 * 动态代理演示
 */
public class DynamicProxyDemonstration {
    public static void main(String[] args) {
        //代理类的真实对象
        Subject realSubject = new RealSubject();

        /**
         * InvocationHandlerImpl 实现了 InvocationHandler 接口，并能实现方法调用从代理类到委托类的分派转发
         * 其内部通常包含指向委托类实例的引用，用于真正执行分派转发过来的方法调用.
         * 即：要代理哪个真实对象，就将该对象传进去，最后是通过该真实对象来调用其方法*/

        InvocationHandler handler = new InvocationHandlerImpl(realSubject);

        ClassLoader loader = realSubject.getClass().getClassLoader();
        Class[] interfaces = realSubject.getClass().getInterfaces();

        /**
         * 该方法用于指定装载器、一组接口及调用处理器生成动态代理类实例
         */
        Subject subject = (Subject) Proxy.newProxyInstance(loader,interfaces,handler);
        System.out.println("动态代理对象的类型："+subject.getClass().getName());

        String hello = subject.SayHello("传");
        System.out.println(hello);

        /**
         * subject.SayHello("jiankunking")这句话时，为什么会自动调用InvocationHandlerImpl的invoke方法？
         * 因为JDK生成的最终真正的代理类，它继承自Proxy并实现了我们定义的Subject接口，
         * 在实现Subject接口方法的内部，通过反射调用了InvocationHandlerImpl的invoke方法。
         * 包含生成本地class文件的demo：
         */


    }
}
