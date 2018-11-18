package cn.edu.xidian.ictt.yk.reflection.p2;

import cn.edu.xidian.ictt.yk.reflection.p3.Person;
import org.junit.Test;

import java.lang.annotation.Annotation;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * Created by heart_sunny on 2018/11/16
 */
public class TestOthers {

    //1.获取运行时类的父类
    @Test
    public void test1() {
        Class clazz = Person.class;
        Class superClass = clazz.getSuperclass();

        System.out.println(superClass); // class cn.edu.xidian.ictt.yk.reflection.p2.Creature
    }

    //2.获取带泛型的父类
    @Test
    public void test2() {
        Class clazz = Person.class;
        Type type1 = clazz.getGenericSuperclass();

        System.out.println(type1); // cn.edu.xidian.ictt.yk.reflection.p2.Creature<java.lang.String>
    }

    //3*.获取父类的泛型
    @Test
    public void test3() {
        Class clazz = Person.class;
        Type type1 = clazz.getGenericSuperclass();
        System.out.println(type1);

        ParameterizedType param = (ParameterizedType) type1;
        Type[] ars = param.getActualTypeArguments();

        System.out.println(((Class) ars[0]).getName()); // java.lang.String
    }

    //4.获取实现的接口
    @Test
    public void test4() {
        Class clazz = Person.class;
        Class[] interfaces = clazz.getInterfaces();

        for (Class i : interfaces) {
            System.out.println(i);
        }

        /*
        interface cn.edu.xidian.ictt.yk.reflection.p2.MyInterface
        interface java.lang.Comparable
         */
    }

    //5.获取所在的包
    @Test
    public void test5(){
        Class clazz = Person.class;
        Package pack = clazz.getPackage();

        System.out.println(pack); // package cn.edu.xidian.ictt.yk.reflection.p3
    }

    //6.获取注解
    @Test
    public void test6(){
        Class clazz = Person.class;
        Annotation[] anns = clazz.getAnnotations();

        for(Annotation a : anns){
            System.out.println(a); // @cn.edu.xidian.ictt.yk.reflection.p2.MyAnnotation(value=yk)
        }
    }

    //7.获取内部类
    @Test
    public void test7() {
        Class clazz = Person.class;
        Class[] cs = clazz.getDeclaredClasses();
        for (Class c: cs) {
            System.out.println(c.getName()); // cn.edu.xidian.ictt.yk.reflection.p3.Person$Bird
        }
    }
}
