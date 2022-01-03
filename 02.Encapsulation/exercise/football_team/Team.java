package T02_Encapsulation.exercise.football_team;

import java.util.ArrayList;
import java.util.List;

public class Team {
    private String name;
    private List<Player> players;

    public Team(String name) {
        this.setName(name);
        this.players = new ArrayList<>();
    }

    private void setName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("A name should not be empty.");
        }
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void addPlayer(Player player) {
        this.players.add(player);
    }

    public void removePlayer(String name) {
        boolean isRemoved = this.players.removeIf(p -> p.getName().equals(name));
        if (!isRemoved) {
            throw new IllegalArgumentException("Player " + name + " is not in " + this.name + " team.");
        }
    }

    public double getRating() {
        return this.players.stream().mapToDouble(Player::overallSkillLevel).average().orElse(0.0);
    }

}
