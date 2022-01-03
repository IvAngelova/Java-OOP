package T05_Polymorphism.exercise.wildFarm;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        List<Animal> animals = new ArrayList<>();
        String evenLine = scan.nextLine();
        while (!evenLine.equals("End")) {
            Animal animal = createAnimal(evenLine.split("\\s+"));

            String oddLine = scan.nextLine();
            Food food = getFood(oddLine.split("\\s+"));

            animal.makeSound();

            try {
                animal.eat(food);
            } catch (IllegalArgumentException ex) {
                System.out.println(ex.getMessage());
            }

            animals.add(animal);

            evenLine = scan.nextLine();
        }

        for (Animal animal : animals) {
            System.out.println(animal.toString());
        }
    }

    private static Food getFood(String[] tokens) {
        int quantity = Integer.parseInt(tokens[1]);
        return tokens[0].equals("Meat")
                ? new Meat(quantity)
                : new Vegetable(quantity);
    }

    private static Animal createAnimal(String[] tokens) {
        String animalType = tokens[0];
        String name = tokens[1];
        double weight = Double.parseDouble(tokens[2]);
        String livingRegion = tokens[3];
        switch (animalType) {
            case "Mouse":
                return new Mouse(name, animalType, weight, livingRegion);
            case "Cat":
                return new Cat(name, animalType, weight, livingRegion, tokens[4]);
            case "Zebra":
                return new Zebra(name, animalType, weight, livingRegion);
            case "Tiger":
                return new Tiger(name, animalType, weight, livingRegion);
            default:
                throw new IllegalStateException("Unknown animal type " + animalType);
        }
    }
}

