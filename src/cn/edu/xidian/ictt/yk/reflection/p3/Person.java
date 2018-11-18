package cn.edu.xidian.ictt.yk.reflection.p3;

import cn.edu.xidian.ictt.yk.reflection.p2.Creature;
import cn.edu.xidian.ictt.yk.reflection.p2.MyAnnotation;
import cn.edu.xidian.ictt.yk.reflection.p2.MyInterface;

/**
 * Created by heart_sunny on 2018/11/16
 */
@MyAnnotation(value = "yk")
public class Person extends Creature<String> implements MyInterface, Comparable {

    public String name;
    private int age;
    int id;

    public static int num = 22;

    public Person() {
    }

    private Person(String name) {
        this.name = name;
    }

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @MyAnnotation(value = "show")
    public void show() {
        System.out.println("I am showing");
    }

    public int display(String nation, int i) {
        System.out.println("nation: " + nation);
        return i;
    }

    private void output() {
        System.out.println("output");
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }

    @Override
    public int compareTo(Object o) {
        return 0;
    }

    public static void info() {
        System.out.println("INFO");
    }

    class Bird {

    }
}
