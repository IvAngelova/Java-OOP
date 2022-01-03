package T07_ReflectionAndAnnotations.lab.getters_and_setters;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Comparator;


public class Main {
    public static void main(String[] args) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        Class<?> clazz = Reflection.class;
        Method[] declaredMethods = clazz.getDeclaredMethods();

        Arrays.stream(declaredMethods)
                .filter(m -> m.getName().contains("get") || m.getName().contains("set"))
                .sorted(Comparator.comparing(Method::getName))
                .forEach(Main::printMethod);
    }

    private static void printMethod(Method method) {
        if (method.getName().contains("get")) {
            System.out.printf("%s will return class %s%n", method.getName(), method.getReturnType().getName());
        } else {
            System.out.printf("%s and will set field of class %s%n", method.getName(), method.getParameterTypes()[0].getName());
        }

    }
}
