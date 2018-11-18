package cn.edu.xidian.ictt.yk.reflection.p4;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * Created by heart_sunny on 2018/11/16
 * <p>
 * 动态代理
 */
interface ClothFactory2 {
    void produceCloth();
}

// 被代理类
class NikeFactory implements ClothFactory2 {

    @Override
    public void produceCloth() {
        System.out.println("NikeFactory");
    }
}

// InvocationHandler
class MyInvocationHandler implements InvocationHandler {

    private Object obj; // 被代理类的对象

    public Object blind(Object obj) {
        this.obj = obj;
        return Proxy.newProxyInstance(obj.getClass().getClassLoader(), obj.getClass().getInterfaces(), this);
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        return method.invoke(obj, args);
    }
}

public class TestClothProduct2 {

    public static void main(String[] args) {

        // 1.被代理类的对象
        NikeFactory nf = new NikeFactory(); // 被代理对象

        // 2.创建一个实现了InvocationHandler接口的类的对象
        MyInvocationHandler mih = new MyInvocationHandler();

        // 3.调用blind()方法，动态地返回一个代理类的对象。
        ClothFactory2 cf = (ClothFactory2) mih.blind(nf); // 代理对象

        // 4.转到对InvocationHandler接口的实现类的invoke()方法的调用
        cf.produceCloth();
    }
}
