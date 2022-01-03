package glacialExpedition.models.mission;

import glacialExpedition.models.explorers.Explorer;
import glacialExpedition.models.states.State;

import java.util.Collection;

public class MissionImpl implements Mission {

    @Override
    public void explore(State state, Collection<Explorer> explorers) {

        for (Explorer explorer : explorers) {
            while (explorer.getEnergy() > 0 && state.getExhibits().size() > 0) {
                String exhibit = state.getExhibits().get(0);
                explorer.search();
                explorer.getSuitcase().getExhibits().add(exhibit);
                state.getExhibits().remove(exhibit);
            }
        }

    }
}
