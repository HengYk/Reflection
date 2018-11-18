package cn.edu.xidian.ictt.yk.reflection.p6;

import java.lang.reflect.Array;
import java.util.Arrays;

/**
 * Created by heart_sunny on 2018/11/18
 *
 * 利用反射编写泛型数组代码
 */
public class CopyOfTest {

    public static Object copyOf(Object obj, int newLength) {
        Class clazz = obj.getClass();
        if (!clazz.isArray()) return null;
        Class componentType = clazz.getComponentType();
        Object object = Array.newInstance(componentType, newLength);
        System.arraycopy(obj, 0, object, 0, Math.min(Array.getLength(obj), newLength));
        return object;
    }

    public static void main(String[] args) {

        int[] a = new int[] {1, 2, 3};
        a = (int[]) copyOf(a, 5);
        System.out.println(Arrays.toString(a));
    }
}
