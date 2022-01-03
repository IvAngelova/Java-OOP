package T01_WorkingWithAbstraction.lab;

import java.util.Scanner;
import java.util.function.Predicate;

public class RhombusOfStars {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int size = readInput();
        String rhombusOfStars = buildRhombusOfStars(size);
        printOutput(rhombusOfStars);

    }

    private static String buildRhombusOfStars(int size) {

      /*  for (int r = 1; r <= size; r++) {
            sb.append(buildLine(size - r, r))
                    .append(System.lineSeparator());
        }

        for (int r = size - 1; r >= 1; r--) {
            sb.append(buildLine(size - r, r))
                    .append(System.lineSeparator());
        }*/

        return multipleRows(1, size, +1, size) +
                multipleRows(size - 1, 1, -1, size);
    }

    private static String multipleRows(int start, int end, int step, int size) {
        StringBuilder sb = new StringBuilder();

        Predicate<Integer> loopCondition = iter -> {
            if (step > 0) {
                return iter <= end;
            }
            return iter >= end;
        };

        for (int r = start; loopCondition.test(r); r += step) {
            sb.append(buildLine(size - r, r))
                    .append(System.lineSeparator());
        }


        return sb.toString();
    }

    private static String buildLine(int spaces, int stars) {
        StringBuilder line = new StringBuilder();
        for (int i = 0; i < spaces; i++) {
            line.append(" ");
        }

        for (int i = 0; i < stars; i++) {
            line.append("* ");
        }

        return line.toString();
    }

    private static void printOutput(String rhombusOfStars) {
        System.out.println(rhombusOfStars);
    }

    private static int readInput() {
        return Integer.parseInt(new Scanner(System.in).nextLine());
    }
}
