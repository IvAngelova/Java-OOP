package T01_WorkingWithAbstraction.lab.hotelReservation;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        String[] input = scan.nextLine().split("\\s+");
        double pricePerDay = Double.parseDouble(input[0]);
        int days = Integer.parseInt(input[1]);
        Season season = Season.valueOf(input[2].toUpperCase());
        Discount discount = Discount.parseDiscount(input[3]);
        PriceCalculator priceCalculator = new PriceCalculator(pricePerDay, days, season, discount);
        System.out.printf("%.2f", priceCalculator.calculatePrice());
    }

}
