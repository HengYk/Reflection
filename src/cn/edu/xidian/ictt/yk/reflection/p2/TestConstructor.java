package cn.edu.xidian.ictt.yk.reflection.p2;

import cn.edu.xidian.ictt.yk.reflection.p3.Person;
import org.junit.Test;

import java.lang.reflect.Constructor;

/**
 * Created by heart_sunny on 2018/11/16
 */
public class TestConstructor {

    @Test
    public void test1() throws Exception {
        String className = "cn.edu.xidian.ictt.yk.reflection.p3.Person";
        Class clazz = Class.forName(className);

        //创建对应的运行时类的对象。使用newInstance()，实际上就是调用了运行时类的空参的构造器。
        //要想能够创建成功：①要求对应的运行时类要有空参的构造器。②构造器的权限修饰符的权限要足够。
        Object obj = clazz.newInstance();
        Person p = (Person) obj;
        System.out.println(p); // Person{name='null', age=0}
    }

    //获取指定的构造器
    @Test
    public void test2() throws ClassNotFoundException {
        String className = "cn.edu.xidian.ictt.yk.reflection.p3.Person";
        Class clazz = Class.forName(className);

        Constructor[] cons = clazz.getDeclaredConstructors();
        for (Constructor c : cons) {
            System.out.println(c);
        }

        /*
        public cn.edu.xidian.ictt.yk.reflection.p3.Person(java.lang.String,int)
        private cn.edu.xidian.ictt.yk.reflection.p3.Person(java.lang.String)
        public cn.edu.xidian.ictt.yk.reflection.p3.Person()
         */
    }

    //调用指定的构造器,创建运行时类的对象
    @Test
    public void test3() throws Exception {
        String className = "cn.edu.xidian.ictt.yk.reflection.p3.Person";
        Class clazz = Class.forName(className);

        Constructor cons = clazz.getDeclaredConstructor(String.class, int.class);
        cons.setAccessible(true);

        Person p = (Person) cons.newInstance("yk", 20);
        System.out.println(p); // Person{name='yk', age=20}
    }
}
