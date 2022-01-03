package T04_InterfacesAndAbstraction.exercise.food_shortage;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int n = Integer.parseInt(scan.nextLine());
        Map<String, Buyer> buyersByName = new HashMap<>();
        while (n-- > 0) {
            Buyer buyer = createBuyer(scan.nextLine());
            buyersByName.put(buyer.getName(), buyer);
        }
        String line = scan.nextLine();
        while (!line.equals("End")) {
            Buyer buyer = buyersByName.get(line); // ако няма такъв, връща null

            if (buyer != null) {
                buyer.buyFood();
            }

            line = scan.nextLine();
        }

        System.out.println(buyersByName.values().stream().mapToInt(Buyer::getFood).sum());
    }

    private static Buyer createBuyer(String line) {
        String[] tokens = line.split("\\s+");
        Buyer buyer;
        if (tokens.length == 4) {
            buyer = new Citizen(tokens[0], Integer.parseInt(tokens[1]), tokens[2], tokens[3]);
        } else {
            buyer = new Rebel(tokens[0], Integer.parseInt(tokens[1]), tokens[2]);
        }
        return buyer;
    }
}
