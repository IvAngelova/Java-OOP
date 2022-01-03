package viceCity.core;

import viceCity.core.interfaces.Controller;
import viceCity.models.guns.Gun;
import viceCity.models.guns.Pistol;
import viceCity.models.guns.Rifle;
import viceCity.models.neighbourhood.GangNeighbourhood;
import viceCity.models.neighbourhood.Neighbourhood;
import viceCity.models.players.CivilPlayer;
import viceCity.models.players.MainPlayer;
import viceCity.models.players.Player;

import java.util.*;

import static viceCity.common.ConstantMessages.*;

public class ControllerImpl implements Controller {
    Player mainPlayer;
    List<Player> civilPlayers;
    Deque<Gun> guns; // queue

    public ControllerImpl() {
        this.mainPlayer = new MainPlayer();
        this.civilPlayers = new ArrayList<>();
        this.guns = new ArrayDeque<>();
    }

    @Override
    public String addPlayer(String name) {
        Player player = new CivilPlayer(name);
        this.civilPlayers.add(player);
        return String.format(PLAYER_ADDED, name);
    }

    @Override
    public String addGun(String type, String name) {
        Gun gun;
        if (type.equals("Pistol")) {
            gun = new Pistol(name);
        } else if (type.equals("Rifle")) {
            gun = new Rifle(name);
        } else {
            return GUN_TYPE_INVALID;
        }
        this.guns.offer(gun);
        return String.format(GUN_ADDED, name, type);
    }

    @Override
    public String addGunToPlayer(String name) {
        if (this.guns.isEmpty()) {
            return GUN_QUEUE_IS_EMPTY;
        }

        Gun gun;
        if (name.equals("Vercetti")) {
            gun = this.guns.poll();
            mainPlayer.getGunRepository().add(gun);
            return String.format(GUN_ADDED_TO_MAIN_PLAYER, gun.getName(), "Tommy Vercetti");
        }

        Player civilPlayer = this.civilPlayers.stream()
                .filter(p -> p.getName().equals(name))
                .findFirst()
                .orElse(null);

        if (civilPlayer == null) {
            return CIVIL_PLAYER_DOES_NOT_EXIST;
        } else {
            gun = this.guns.poll();
            civilPlayer.getGunRepository().add(gun);
            return String.format(GUN_ADDED_TO_CIVIL_PLAYER, gun.getName(), name);
        }

    }

    @Override
    public String fight() {
        int lifePointsBeforeFightMainPlayer = this.mainPlayer.getLifePoints();
        int sumLifePointsCivilPlayersBeforeFight = this.civilPlayers.stream()
                .mapToInt(Player::getLifePoints)
                .sum();
        int countCivilPlayersBeforeFight = this.civilPlayers.size();

        Neighbourhood neighbourhood = new GangNeighbourhood();
        neighbourhood.action(this.mainPlayer, this.civilPlayers);

        int lifePointsAfterFightMainPlayer = this.mainPlayer.getLifePoints();
        int sumLifePointsCivilPlayersAfterFight = this.civilPlayers.stream()
                .mapToInt(Player::getLifePoints)
                .sum();

        if (lifePointsBeforeFightMainPlayer == lifePointsAfterFightMainPlayer &&
                sumLifePointsCivilPlayersBeforeFight == sumLifePointsCivilPlayersAfterFight) {
            return FIGHT_HOT_HAPPENED;
        } else {
            StringBuilder sb = new StringBuilder();
            sb.append(FIGHT_HAPPENED).append(System.lineSeparator());
            sb.append(String.format(MAIN_PLAYER_LIVE_POINTS_MESSAGE, this.mainPlayer.getLifePoints()))
                    .append(System.lineSeparator());
            sb.append(String.format(MAIN_PLAYER_KILLED_CIVIL_PLAYERS_MESSAGE,
                    countCivilPlayersBeforeFight - this.civilPlayers.size())).append(System.lineSeparator());
            sb.append(String.format(CIVIL_PLAYERS_LEFT_MESSAGE, this.civilPlayers.size()));
            return sb.toString();
        }
    }
}
