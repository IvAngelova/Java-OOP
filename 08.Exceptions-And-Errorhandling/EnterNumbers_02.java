package T08_ExceptionsAndErrorHandling;

import java.util.Scanner;
import java.util.stream.IntStream;

public class EnterNumbers_02 {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        while (true) {
            String start = scan.nextLine();
            String end = scan.nextLine();

            try {
                printNumberRange(start, end);
                break;
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private static void printNumberRange(String start, String end) {
        int s;
        int e;
        try {
            s = Integer.parseInt(start);
            e = Integer.parseInt(end);
        } catch (NumberFormatException ex) {
            throw new IllegalArgumentException("Invalid input, please enter new range", ex);
        }

        if (!(1 < s && s < e && e < 120)) {
            throw new IllegalArgumentException("Invalid input, please enter new range");
        }

        IntStream
                .rangeClosed(s, e)
                .forEach(System.out::println);
    }
}
