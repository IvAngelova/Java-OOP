package T07_ReflectionAndAnnotations.lab.high_quality_mistakes;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.Comparator;


public class Main {
    public static void main(String[] args) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        Class<?> clazz = Reflection.class;

        Field[] declaredFields = clazz.getDeclaredFields();

        Arrays.stream(declaredFields)
                .sorted(Comparator.comparing(Field::getName))
                .filter(f -> !Modifier.isPrivate(f.getModifiers()))
                .forEach(f -> {
                    System.out.println(f.getName() + " must be private!");
                });

        Method[] declaredMethods = clazz.getDeclaredMethods();

        Arrays.stream(declaredMethods)
                .filter(m -> m.getName().contains("get") || m.getName().contains("set"))
                .sorted(Comparator.comparing(Method::getName))
                .forEach(Main::printMethod);
    }

    private static void printMethod(Method method) {
        if (method.getName().contains("get") && !Modifier.isPublic(method.getModifiers())) {
            System.out.printf("%s have to be public!%n", method.getName());
        } else if(method.getName().contains("set") && !Modifier.isPrivate(method.getModifiers())) {
            System.out.printf("%s have to be private!%n", method.getName());
        }

    }
}
