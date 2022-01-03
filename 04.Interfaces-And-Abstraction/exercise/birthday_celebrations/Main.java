package T04_InterfacesAndAbstraction.exercise.birthday_celebrations;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        String input = scan.nextLine();
        List<Birthable> allWithBirthDate = new ArrayList<>();

        while (!input.equals("End")) {
            String[] tokens = input.split("\\s+");
            switch (tokens[0]) {
                case "Citizen":
                    Citizen citizen = new Citizen(tokens[1], Integer.parseInt(tokens[2]), tokens[3], tokens[4]);
                    allWithBirthDate.add(citizen);
                    break;
                case "Pet":
                    Pet pet = new Pet(tokens[1], tokens[2]);
                    allWithBirthDate.add(pet);
                    break;
                case "Robot":
                    Robot robot = new Robot(tokens[2], tokens[1]);
                    break;
            }
            input = scan.nextLine();
        }

        String year = scan.nextLine();
        boolean isFound = false;

        for (Birthable birthable : allWithBirthDate) {
            if (birthable.getBirthDate().endsWith(year)) {
                System.out.println(birthable.getBirthDate());
                isFound = true;
            }
        }
        if (!isFound) {
            System.out.println("<no output>");
        }
    }
}

