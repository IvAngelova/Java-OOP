package spaceStation.models.mission;

import spaceStation.models.astronauts.Astronaut;
import spaceStation.models.planets.Planet;

import java.util.Collection;

public class MissionImpl implements Mission{
    @Override
    public void explore(Planet planet, Collection<Astronaut> astronauts) {
        for (Astronaut astronaut : astronauts) {
            while (astronaut.getOxygen() > 0 && !planet.getItems().isEmpty()){
                String item = planet.getItems().get(0);
                planet.getItems().remove(item);
                astronaut.breath();
                astronaut.getBag().getItems().add(item);
            }
        }
    }
}
