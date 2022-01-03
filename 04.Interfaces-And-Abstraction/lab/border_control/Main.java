package T04_InterfacesAndAbstraction.lab.border_control;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        String line = scan.nextLine();
        List<Identifiable> identifiables = new ArrayList<>();

        while (!line.equals("End")) {
            String[] tokens = line.split("\\s+");
            if (tokens.length == 2) {
                Identifiable robot = new Robot(tokens[0], tokens[1]);
                identifiables.add(robot);
            } else {
                Identifiable citizen = new Citizen(tokens[0], Integer.parseInt(tokens[1]), tokens[2]);
                identifiables.add(citizen);
            }
            line = scan.nextLine();
        }

        String fakeIdPostfix = scan.nextLine();
        for (Identifiable identifiable : identifiables) {
            if (identifiable.getId().endsWith(fakeIdPostfix)) {
                System.out.println(identifiable.getId());
            }
        }
    }
}
