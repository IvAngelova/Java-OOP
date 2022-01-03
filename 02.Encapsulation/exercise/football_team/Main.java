package T02_Encapsulation.exercise.football_team;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        String line = scan.nextLine();
        Map<String, Team> teams = new LinkedHashMap<>();

        while (!line.equals("END")) {
            String[] tokens = line.split(";");

            String command = tokens[0];
            String teamName = tokens[1];

            try {
                switch (command) {
                    case "Team":
                        teams.putIfAbsent(teamName, new Team(teamName));
                        break;
                    case "Add":
                        String playerName = tokens[2];
                        int endurance = Integer.parseInt(tokens[3]);
                        int sprint = Integer.parseInt(tokens[4]);
                        int dribble = Integer.parseInt(tokens[5]);
                        int passing = Integer.parseInt(tokens[6]);
                        int shooting = Integer.parseInt(tokens[7]);

                        if (!teams.containsKey(teamName)) {
                            System.out.println("Team " + teamName + " does not exist.");
                        } else {
                            Player player = new Player(playerName, endurance, sprint, dribble, passing, shooting);
                            teams.get(teamName).addPlayer(player);
                        }
                        break;
                    case "Remove":
                        String playerToRemove = tokens[2];
                        teams.get(teamName).removePlayer(playerToRemove);
                        break;
                    case "Rating":
                        if (!teams.containsKey(teamName)) {
                            System.out.println("Team " + teamName + " does not exist.");
                        } else {
                            System.out.printf("%s - %d%n", teamName, Math.round(teams.get(teamName).getRating()));
                        }
                        break;
                }

            } catch (IllegalArgumentException ex) {
                System.out.println(ex.getMessage());
            }

            line = scan.nextLine();

        }
    }
}
