package viceCity.models.neighbourhood;

import viceCity.models.guns.Gun;
import viceCity.models.players.Player;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class GangNeighbourhood implements Neighbourhood {
    @Override
    public void action(Player mainPlayer, List<Player> civilPlayers) {

        label:
        if (!mainPlayer.getGunRepository().getModels().isEmpty()) {
            Gun gunMainPlayer = mainPlayer.getGunRepository().getModels().remove(0);

            for (Player civilPlayer : civilPlayers) {
                while (civilPlayer.isAlive()) {
                    if (gunMainPlayer.getTotalBullets() == 0 && gunMainPlayer.getBulletsPerBarrel() == 0) {
                        if (!mainPlayer.getGunRepository().getModels().isEmpty()) {
                            gunMainPlayer = mainPlayer.getGunRepository().getModels().remove(0);
                        } else {
                            break label;
                        }
                    }
                    int shotBullets = gunMainPlayer.fire();
                    civilPlayer.takeLifePoints(shotBullets);

                }
            }
        }

        List<Player> deadPlayers = civilPlayers.stream().filter(p -> !p.isAlive()).collect(Collectors.toList());
        civilPlayers.removeAll(deadPlayers);

        for (Player civilPlayer : civilPlayers) {
            label:
            if (!civilPlayer.getGunRepository().getModels().isEmpty()) {
                Gun gunCivilPlayer = civilPlayer.getGunRepository().getModels().remove(0);
                while (mainPlayer.isAlive()) {
                    if (gunCivilPlayer.getTotalBullets() == 0 && gunCivilPlayer.getBulletsPerBarrel() == 0) {
                        if (!civilPlayer.getGunRepository().getModels().isEmpty()) {
                            gunCivilPlayer = civilPlayer.getGunRepository().getModels().remove(0);
                        } else {
                            break label;
                        }
                    }
                    int shotBullets = gunCivilPlayer.fire();
                    mainPlayer.takeLifePoints(shotBullets);

                }
            }
        }

    }
}
