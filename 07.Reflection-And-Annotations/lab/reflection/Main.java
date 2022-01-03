package T07_ReflectionAndAnnotations.lab.reflection;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class Main {
    public static void main(String[] args) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        Class<?> clazz = Reflection.class;
        System.out.println(clazz);
        Class<?> superclass = clazz.getSuperclass();
        System.out.println(superclass);
        for (Class<?> anInterface : clazz.getInterfaces()) {
            System.out.println(anInterface);
        }
        Constructor<?> constructor = clazz.getDeclaredConstructor();
        Object obj = constructor.newInstance();
        System.out.println(obj);

    }

}
