package T05_Polymorphism.exercise.wildFarm;

public abstract class Food {
    private int quantity;

    protected Food(int quantity) {
        this.quantity = quantity;
    }

    protected int getQuantity() {
        return quantity;
    }
}
