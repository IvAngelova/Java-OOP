package bakery.entities.bakedFoods;

public class Cake extends BaseFood {
    private static final double PORTION = 245;

    public Cake(String name, double price) {
        super(name, PORTION, price);
    }
}
