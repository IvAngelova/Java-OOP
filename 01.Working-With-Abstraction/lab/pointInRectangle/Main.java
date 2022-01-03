package T01_WorkingWithAbstraction.lab.pointInRectangle;

import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int[] pointsInRectangle = Arrays.stream(scan.nextLine().split("\\s+"))
                .mapToInt(Integer::parseInt)
                .toArray();
        Point bottomLeft = new Point(pointsInRectangle[0], pointsInRectangle[1]);
        Point topRight = new Point(pointsInRectangle[2], pointsInRectangle[3]);
        Rectangle rectangle = new Rectangle(bottomLeft, topRight);

        int n = Integer.parseInt(scan.nextLine());
        while (n-- > 0) {
            int[] coordinates = Arrays.stream(scan.nextLine().split("\\s+"))
                    .mapToInt(Integer::parseInt)
                    .toArray();
            Point p = new Point(coordinates[0], coordinates[1]);
            boolean isWithin = rectangle.contains(p);
            System.out.println(isWithin);
        }
    }
}
