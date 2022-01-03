package T05_Polymorphism.exercise.vehicles;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        String[] input = scan.nextLine().split("\\s+");
        Map<String, Vehicle> vehicleMap = new LinkedHashMap<>();

        vehicleMap.put("Car", new Car(Double.parseDouble(input[1]), Double.parseDouble(input[2])));
        input = scan.nextLine().split("\\s+");
        vehicleMap.put("Truck", new Truck(Double.parseDouble(input[1]), Double.parseDouble(input[2])));

        int countCommands = Integer.parseInt(scan.nextLine());

        for (int i = 0; i < countCommands; i++) {
            String command = scan.nextLine();
            input = command.split("\\s+");
            if (command.contains("Drive")) {
                System.out.println(vehicleMap.get(input[1]).drive(Double.parseDouble(input[2])));
            } else {
                vehicleMap.get(input[1]).refuel(Double.parseDouble(input[2]));
            }
        }

        for (Vehicle vehicle : vehicleMap.values()) {
            System.out.println(vehicle.toString());
        }
    }
}
