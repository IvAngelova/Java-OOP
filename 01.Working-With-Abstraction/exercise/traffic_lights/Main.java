package T01_WorkingWithAbstraction.exercise.traffic_lights;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        Color[] signals = Arrays.stream(scan.nextLine().split("\\s+"))
                .map(Color::valueOf).toArray(Color[]::new);
        int n = Integer.parseInt(scan.nextLine());
        List<TrafficLight> trafficLights = new ArrayList<>();

        for (Color signal : signals) {
            TrafficLight trafficLight = new TrafficLight(signal);
            trafficLights.add(trafficLight);
        }

        for (int i = 0; i < n; i++) {
            for (TrafficLight trafficLight : trafficLights) {
                trafficLight.changeColor();
                System.out.print(trafficLight.getColor().toString() + " ");
            }
            System.out.println();
        }
    }
}

