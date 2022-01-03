package CounterStriker.models.field;

import CounterStriker.models.players.Player;

import java.util.List;
import java.util.stream.Collectors;

import static CounterStriker.common.OutputMessages.COUNTER_TERRORIST_WINS;
import static CounterStriker.common.OutputMessages.TERRORIST_WINS;

public class FieldImpl implements Field {
    @Override
    public String start(List<Player> players) {
        List<Player> terrorists = players.stream()
                .filter(p -> p.getClass().getSimpleName().equals("Terrorist"))
                .collect(Collectors.toList());
        List<Player> counterTerrorists = players.stream()
                .filter(p -> p.getClass().getSimpleName().equals("CounterTerrorist"))
                .collect(Collectors.toList());

        while (!counterTerrorists.isEmpty() && !terrorists.isEmpty()) {

            for (Player terrorist : terrorists) {
                for (Player counterTerrorist : counterTerrorists) {
                    int fire = terrorist.getGun().fire();
                    counterTerrorist.takeDamage(fire);
                }
            }

            List<Player> deadCounterTerrorists = counterTerrorists.stream()
                    .filter(p -> !p.isAlive())
                    .collect(Collectors.toList());

            counterTerrorists.removeAll(deadCounterTerrorists);

            for (Player counterTerrorist : counterTerrorists) {
                for (Player terrorist : terrorists) {
                    int fire = counterTerrorist.getGun().fire();
                    terrorist.takeDamage(fire);

                }
            }

            List<Player> deadTerrorists = terrorists.stream()
                    .filter(p -> !p.isAlive())
                    .collect(Collectors.toList());

            terrorists.removeAll(deadTerrorists);

        }

        if (terrorists.isEmpty()) {
            return COUNTER_TERRORIST_WINS;
        }

        return TERRORIST_WINS;
    }
}
