package T07_ReflectionAndAnnotations.lab.coding_tracker;

import java.util.Arrays;
import java.util.Comparator;

public class Tracker {

    @Author(name = "Peter")
    public static void main(String[] args) {
        Tracker.printMethodsByAuthor(Tracker.class);
    }
    
    @Author(name = "George")
    public static void printMethodsByAuthor(Class<?> clazz) {
        Arrays.stream(clazz.getDeclaredMethods())
                .filter(m -> m.getDeclaredAnnotation(Author.class) != null)
                .sorted(Comparator.comparing(m -> m.getDeclaredAnnotation(Author.class).name()))
                .forEach(m -> System.out.println(m.getDeclaredAnnotation(Author.class).name() + ": " + m.getName()));
    }
}
