package T05_Polymorphism.lab.animals;

public class Dog extends Animal {
    public Dog(String name, String favouriteFood) {
        super(name, favouriteFood);
    }

    @Override
    public String explainSelf() {
        return String.format("I am %s and my favourite food is %s\n" +
                "DJAAF", super.getName(), super.getFavouriteFood());
    }
}
