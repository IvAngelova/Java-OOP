package T05_Polymorphism.exercise.vehicles_extended;

public class Car extends Vehicle{
    private static final double AIR_CONDITIONER_ADDITIONAL_CONSUMPTION = 0.9;

    public Car(double fuelQuantity, double fuelConsumption, double tankCapacity) {
        super(fuelQuantity, fuelConsumption + AIR_CONDITIONER_ADDITIONAL_CONSUMPTION, tankCapacity);
    }
}
