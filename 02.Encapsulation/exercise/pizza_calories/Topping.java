package T02_Encapsulation.exercise.pizza_calories;

public class Topping {
    private String toppingType;
    private double weight;

    public Topping(String toppingType, double weight) {
        this.setToppingType(toppingType);
        this.setWeight(weight);
    }

    private void setToppingType(String toppingType) {
        switch (toppingType) {
            case "Meat":
            case "Veggies":
            case "Cheese":
            case "Sauce":
                this.toppingType = toppingType;
                break;
            default:
                String msg = String.format("Cannot place %s on top of your pizza.", toppingType);
                throw new IllegalArgumentException(msg);
        }
    }

    private void setWeight(double weight) {
        if (weight < 1 || weight > 50) {
            String msg = String.format("%s weight should be in the range [1..50].", this.toppingType);
            throw new IllegalArgumentException(msg);
        }
        this.weight = weight;
    }

    public double calculateCalories() {
        double toppingModifier = getToppingModifier();
        return (2 * weight) * toppingModifier;
    }

    private double getToppingModifier() {
        switch (this.toppingType) {
            case "Meat":
                return 1.2;
            case "Veggies":
                return 0.8;
            case "Cheese":
                return 1.1;
            case "Sauce":
                return 0.9;
            default:
                return 0;
        }
    }
}
