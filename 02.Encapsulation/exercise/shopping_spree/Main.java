package T02_Encapsulation.exercise.shopping_spree;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        String[] people = scan.nextLine().split(";");

        Map<String, Person> peopleInfo = new LinkedHashMap<>();
        Map<String, Product> productsInfo = new HashMap<>();

        for (String element : people) {
            String[] personData = element.split("=");
            String name = personData[0];
            double money = Double.parseDouble(personData[1]);

            try {
                Person person = new Person(name, money);
                peopleInfo.put(person.getName(), person);
            } catch (IllegalArgumentException ex) {
                System.out.println(ex.getMessage());
                return;
            }
        }

        String[] products = scan.nextLine().split(";");

        for (String element : products) {
            String[] productData = element.split("=");
            String name = productData[0];
            double cost = Double.parseDouble(productData[1]);

            try {
                Product product = new Product(name, cost);
                productsInfo.put(product.getName(), product);
            } catch (IllegalArgumentException ex) {
                System.out.println(ex.getMessage());
                return;
            }
        }

        String line = scan.nextLine();

        while (!line.equals("END")) {
            String[] tokens = line.split("\\s+");
            String person = tokens[0];
            String product = tokens[1];

            try {
                peopleInfo.get(person).buyProduct(productsInfo.get(product));
                System.out.printf("%s bought %s%n", person, product);
            } catch (IllegalArgumentException ex) {
                System.out.println(ex.getMessage());
            }

            line = scan.nextLine();
        }

        for (Person person : peopleInfo.values()) {
            System.out.print(person.getName() + " - ");
            if (person.getProducts().isEmpty()) {
                System.out.println("Nothing bought");
            } else {
                System.out.println(person.getProducts().stream()
                        .map(Product::getName)
                        .collect(Collectors.joining(", ")));
            }
        }

    }
}
