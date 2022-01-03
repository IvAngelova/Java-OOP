package bakery.entities.bakedFoods;

public class Bread extends BaseFood {
    private static final double PORTION = 200;

    public Bread(String name, double price) {
        super(name, PORTION, price);

    }
}
