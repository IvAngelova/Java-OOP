package T05_Polymorphism.exercise.wildFarm;

public class Cat extends Felime {
    private String breed;

    public Cat(String name, String type, double weight, String livingRegion, String breed) {
        super(name, type, weight, livingRegion);
        this.breed = breed;
    }

    @Override
    public void makeSound() {
        System.out.println("Meowwww");
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder(super.toString());
        sb.insert(sb.indexOf(",") + 1, " " + this.breed + ",");
        return sb.toString();
    }
}
