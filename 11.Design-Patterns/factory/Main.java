package T09_DesignPatterns.factory;

public class Main {
    public static void main(String[] args) {

        Pizzeria pizzeria = new Pizzeria(new PizzaFactory());
        pizzeria.orderPizza(PizzaType.CHEESE, 30);
        pizzeria.orderPizza(PizzaType.MEAT, 50);
    }
}
