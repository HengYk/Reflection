package cn.edu.xidian.ictt.yk.reflection.p5;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * Created by heart_sunny on 2018/11/16
 */
interface Human {
    void info();

    void fly(String str);
}

// 被代理类
class SuperMan implements Human {

    @Override
    public void info() {
        System.out.println("INFO");
    }

    @Override
    public void fly(String str) {
        System.out.println("FLY" + str);
    }
}

class SuperManProxy {

    // 获得代理类
    public static Object getProxyObject(Object object) {
        MyInvocationHandler mih = new MyInvocationHandler();
        mih.setObject(object);

        return Proxy.newProxyInstance(object.getClass().getClassLoader(), object.getClass().getInterfaces(), mih);
    }
}

// InvocationHandler
class MyInvocationHandler implements InvocationHandler {

    private Object object;

    public void setObject(Object object) {
        this.object = object;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws InvocationTargetException, IllegalAccessException {
        HumanUtil humanUtil = new HumanUtil();
        humanUtil.methodOne();

        Object obj = method.invoke(object, args);

        humanUtil.methodTwo();

        return obj;
    }
}

class HumanUtil {
    public void methodOne() {
        System.out.println("method-1");
    }

    public void methodTwo() {
        System.out.println("method-2");
    }
}

public class TestAOP {

    public static void main(String[] args) {

        SuperMan sm = new SuperMan();
        Human h = (Human) SuperManProxy.getProxyObject(sm);

        h.info();

        System.out.println();

        h.fly("higher");

        /*
        method-1
        INFO
        method-2

        method-1
        FLYhigher
        method-2
         */
    }
}
