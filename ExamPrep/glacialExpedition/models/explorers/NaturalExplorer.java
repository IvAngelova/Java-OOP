package glacialExpedition.models.explorers;

public class NaturalExplorer extends BaseExplorer{

    private static final double INITIAL_ENERGY = 60;

    public NaturalExplorer(String name) {
        super(name, INITIAL_ENERGY);
    }

    @Override
    public void search() {
        try {
            setEnergy(this.getEnergy() - 7);
        } catch (IllegalArgumentException ex) {
            setEnergy(0);
        }
    }
}
