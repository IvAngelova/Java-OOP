package T05_Polymorphism.exercise.wildFarm;

import java.text.DecimalFormat;

public abstract class Animal {
    private String name;
    private String type;
    private double weight;
    private String livingRegion;
    private int foodEaten;

    protected Animal(String name, String type, double weight, String livingRegion) {
        this.name = name;
        this.type = type;
        this.weight = weight;
        this.livingRegion = livingRegion;
        this.foodEaten = 0;
    }

    public abstract void makeSound();

    public void eat(Food food) {
        this.foodEaten += food.getQuantity();
    }

    @Override
    public String toString() {
        DecimalFormat df = new DecimalFormat("##.##");
        return String.format("%s[%s, %s, %s, %d]", this.type, this.name,
                df.format(this.weight), this.livingRegion, this.foodEaten);
    }
}
