package T07_ReflectionAndAnnotations.exercise.blackBoxInteger;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException, NoSuchFieldException {
        Class<BlackBoxInt> clazz = BlackBoxInt.class;
        Constructor<BlackBoxInt> constructor = clazz.getDeclaredConstructor();
        constructor.setAccessible(true);
        BlackBoxInt blackBoxInt = constructor.newInstance();
        Field innerValue = clazz.getDeclaredField("innerValue");
        innerValue.setAccessible(true);
        Method[] declaredMethods = clazz.getDeclaredMethods();

        Scanner scan = new Scanner(System.in);
        String input = scan.nextLine();
        while (!input.equals("END")) {
            String methodName = input.split("_")[0];
            int value = Integer.parseInt(input.split("_")[1]);

            Method method = getMethod(declaredMethods, methodName);

            method.setAccessible(true);
            method.invoke(blackBoxInt, value);
            System.out.println(innerValue.get(blackBoxInt));

            input = scan.nextLine();
        }

    }

    private static Method getMethod(Method[] declaredMethods, String methodName) {
        return Arrays.stream(declaredMethods)
                .filter(m -> m.getName().equals(methodName))
                .findFirst()
                .get();
    }
}
