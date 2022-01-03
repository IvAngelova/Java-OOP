package T07_ReflectionAndAnnotations.exercise.harvestingFields;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.Scanner;
import java.util.function.Consumer;

public class Main {
    public static void main(String[] args) {
        Class<RichSoilLand> clazz = RichSoilLand.class;
        Field[] declaredFields = clazz.getDeclaredFields();
        Scanner scan = new Scanner(System.in);
        String input = scan.nextLine();
        Consumer<Field> printer = f -> System.out.println(Modifier.toString(f.getModifiers()) + " "
                + f.getType().getSimpleName() + " " + f.getName());
        while (!input.equals("HARVEST")) {
            switch (input) {
                case "public":
                    Arrays.stream(declaredFields).filter(f -> Modifier.isPublic(f.getModifiers()))
                            .forEach(printer);
                    break;
                case "private":
                    Arrays.stream(declaredFields).filter(f -> Modifier.isPrivate(f.getModifiers()))
                            .forEach(printer);
                    break;
                case "protected":
                    Arrays.stream(declaredFields).filter(f -> Modifier.isProtected(f.getModifiers()))
                            .forEach(printer);
                    break;
                case "all":
                    Arrays.stream(declaredFields)
                            .forEach(printer);
                    break;
            }
            input = scan.nextLine();
        }

    }
}
