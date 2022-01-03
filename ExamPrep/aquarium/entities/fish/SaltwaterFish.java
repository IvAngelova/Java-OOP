package aquarium.entities.fish;

public class SaltwaterFish extends BaseFish {
    private static final int SIZE = 5;

    public SaltwaterFish(String name, String species, double price) {
        super(name, species, price);
        this.setSize(SIZE);
    }

    @Override
    public void eat() {
        this.setSize(this.getSize() + 2);
    }
}
