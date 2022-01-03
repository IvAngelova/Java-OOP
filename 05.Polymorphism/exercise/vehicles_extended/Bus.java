package T05_Polymorphism.exercise.vehicles_extended;

public class Bus extends Vehicle {
    private static final double AIR_CONDITIONER_ADDITIONAL_CONSUMPTION = 1.4;

    protected Bus(double fuelQuantity, double fuelConsumption, double tankCapacity) {
        super(fuelQuantity, fuelConsumption, tankCapacity);
    }

    public String driveWithPassengers(double distance) {
        return super.doWithAdditionalConsumption(AIR_CONDITIONER_ADDITIONAL_CONSUMPTION, () -> super.drive(distance));
    }

}
