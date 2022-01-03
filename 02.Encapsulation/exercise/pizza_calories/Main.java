package T02_Encapsulation.exercise.pizza_calories;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        String[] pizzaInfo = scan.nextLine().split("\\s+");
        String pizzaName = pizzaInfo[1];
        int numberOfToppings = Integer.parseInt(pizzaInfo[2]);

        String[] doughInfo = scan.nextLine().split("\\s+");
        String flourType = doughInfo[1];
        String bakingTechnique = doughInfo[2];
        double weightDough = Double.parseDouble(doughInfo[3]);

        try {
            Pizza pizza = new Pizza(pizzaName, numberOfToppings);
            Dough dough = new Dough(flourType, bakingTechnique, weightDough);
            pizza.setDough(dough);
            String toppingInfo = scan.nextLine();
            while (!toppingInfo.equals("END")) {
                String toppingType = toppingInfo.split("\\s+")[1];
                double weightTopping = Double.parseDouble(toppingInfo.split("\\s+")[2]);
                Topping topping = new Topping(toppingType, weightTopping);
                pizza.addTopping(topping);
                toppingInfo = scan.nextLine();
            }
            System.out.printf("%s - %.2f", pizza.getName(), pizza.getOverallCalories());
        } catch (IllegalArgumentException ex) {
            System.out.println(ex.getMessage());
        }


    }
}
