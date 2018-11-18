package cn.edu.xidian.ictt.yk.reflection.p1;

import cn.edu.xidian.ictt.yk.reflection.p3.Person;
import org.junit.Test;

import java.io.*;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Properties;

/**
 * Created by heart_sunny on 2018/11/16
 */
public class TestClass {

    // 在有反射以前，如何创建一个类的对象，并调用其中的方法、属性
    @Test
    public void test1() {
        Person per = new Person();
        per.setName("yk");
        per.setAge(18);

        System.out.println(per);

        per.show();
        per.display("China", 1);
    }

    // 有了反射，可以通过反射创建一个类的对象，并调用其中的结构
    @Test
    public void test2() {

        Class clazz = Person.class;

        //1.创建clazz对应的运行时类Person类的对象
        try {
            Object obj = clazz.newInstance();
            System.out.println(obj); // Person{name='null', age=0}
        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }

        //2.通过反射调用运行时类的指定的属性
        try {
            Person p = (Person) clazz.newInstance();
            Field f = clazz.getField("name");
            f.set(p, "yk");
            System.out.println(p); // Person{name='yk', age=0}

            Field f1 = clazz.getDeclaredField("age");
            f1.setAccessible(true); // 增加访问权限
            f1.set(p, 18);
            System.out.println(p); // Person{name='yk', age=18}

            System.out.println(f.get(p)); // yk
            System.out.println(f1.get(p)); // 18
        } catch (NoSuchFieldException | IllegalAccessException | InstantiationException e) {
            e.printStackTrace();
        }

        //3.通过反射调用运行时类的指定的方法
        try {
            Person p = (Person) clazz.newInstance();
            Method m = clazz.getMethod("show");
            m.invoke(p); // I am showing

            Method m1 = clazz.getDeclaredMethod("display", String.class, int.class);
            Object obj = m1.invoke(p, "USA", 2); // nation: USA
            System.out.println(obj); // 2
        } catch (InstantiationException | NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    /*
     * java.lang.Class:是反射的源头。
     * 我们创建了一个类，通过编译（javac.exe）,生成对应的.class文件。之后我们使用java.exe加载（JVM的类加载器完成的）
     * 此.class文件，此.class文件加载到内存以后，就是一个运行时类，存在在缓存区。那么这个运行时类本身就是一个Class的实例！
     * 1.每一个运行时类只加载一次！
     * 2.有了Class的实例以后，我们才可以进行如下的操作：
     *     1）*创建对应的运行时类的对象
     *     2）获取对应的运行时类的完整结构（属性、方法、构造器、内部类、父类、所在的包、异常、注解、...）
     *     3）*调用对应的运行时类的指定的结构(属性、方法、构造器)
     *     4）反射的应用：动态代理
     */
    @Test
    public void test3() {
        Person p = new Person();
        Class clazz = p.getClass();
        System.out.println(clazz); // class cn.edu.xidian.ictt.yk.reflection.p3.Person
    }

    // 如何获取Class的实例（3种）
    @Test
    public void test4() throws ClassNotFoundException {

        //1.调用运行时类本身的.class属性
        Class clazz = Person.class;
        System.out.println(clazz.getName());

        //2.通过运行时类的对象获取
        Person p = new Person();
        Class clazz1 = p.getClass();
        System.out.println(clazz1.getName());

        //3.通过Class的静态方法获取。通过此方式，体会一下，反射的动态性。
        String className = "cn.edu.xidian.ictt.yk.reflection.p3.Person";
        Class clazz2 = Class.forName(className);
        System.out.println(clazz2.getName());

        //4.（了解）通过类的加载器
        ClassLoader cl = this.getClass().getClassLoader();
        Class clazz3 = cl.loadClass(className);
        System.out.println(clazz3.getName());

        assert clazz == clazz1;
        assert clazz == clazz2;
        assert clazz == clazz3;

        /*
        cn.edu.xidian.ictt.yk.reflection.p3.Person
        cn.edu.xidian.ictt.yk.reflection.p3.Person
        cn.edu.xidian.ictt.yk.reflection.p3.Person
        cn.edu.xidian.ictt.yk.reflection.p3.Person
         */
    }

    // 关于类加载器：ClassLoader
    @Test
    public void test5() throws ClassNotFoundException {

        //系统类加载器
        ClassLoader c1 =  ClassLoader.getSystemClassLoader();
        System.out.println(c1); // sun.misc.Launcher$AppClassLoader@18b4aac2

        //扩展类加载器
        ClassLoader c2 = c1.getParent();
        System.out.println(c2); // sun.misc.Launcher$ExtClassLoader@8efb846

        //引导类加载器
        ClassLoader c3 = c2.getParent();
        System.out.println(c3); // null

        //获取指定类对象的类加载器
        String className = "java.util.Random";
        Class clazz = Class.forName(className);
        ClassLoader c4 = clazz.getClassLoader();
        System.out.println(c4); // null

        String className1 = "cn.edu.xidian.ictt.yk.reflection.p3.Person";
        Class clazz1 = Class.forName(className1);
        ClassLoader c5 = clazz1.getClassLoader();
        System.out.println(c5); // sun.misc.Launcher$AppClassLoader@18b4aac2

        //获取工程路径下指定文件的输入流yi
        try {
            FileInputStream fis = new FileInputStream(new File("jdbc1.properties"));
            System.out.println(fis.available()); // 29
        } catch (Exception e) {
            e.printStackTrace();
        }

        //获取某类路径下指定文件的输入流er
        try {
            ClassLoader c6 = this.getClass().getClassLoader();
            InputStream is = c6.getResourceAsStream("cn\\edu\\xidian\\ictt\\yk\\reflection\\p1\\jdbc.properties");
            Properties properties = new Properties();
            properties.load(is);
            System.out.println(properties.getProperty("username")); // yk
            System.out.println(properties.getProperty("password")); // 123456
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
