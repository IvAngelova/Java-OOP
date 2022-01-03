package T08_ExceptionsAndErrorHandling;

import java.util.Scanner;

public class SquareRoot_01 {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        try {
            double sqrt = getSqrt(scan);
            System.out.println(sqrt);
        } catch (NumberFormatException e) {
            System.out.println(e.getMessage());
        } finally {
            System.out.println("Good bye");
        }
    }

    private static double getSqrt(Scanner scan) {
        String number = scan.nextLine();
        for (char c : number.toCharArray()) {
            if (!Character.isDigit(c)) {
                throw new NumberFormatException("Invalid number");
            }
        }
        int num = Integer.parseInt(number);
        return Math.sqrt(num);
    }
}
