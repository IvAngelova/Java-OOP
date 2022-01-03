package bakery.core;

import bakery.core.interfaces.Controller;
import bakery.entities.bakedFoods.Bread;
import bakery.entities.bakedFoods.Cake;
import bakery.entities.bakedFoods.interfaces.BakedFood;
import bakery.entities.drinks.Tea;
import bakery.entities.drinks.Water;
import bakery.entities.drinks.interfaces.Drink;
import bakery.entities.tables.InsideTable;
import bakery.entities.tables.OutsideTable;
import bakery.entities.tables.interfaces.Table;
import bakery.repositories.interfaces.*;

import static bakery.common.ExceptionMessages.*;
import static bakery.common.OutputMessages.*;

public class ControllerImpl implements Controller {
    private FoodRepository<BakedFood> foodRepository;
    private DrinkRepository<Drink> drinkRepository;
    private TableRepository<Table> tableRepository;
    private double totalIncome;

    public ControllerImpl(FoodRepository<BakedFood> foodRepository, DrinkRepository<Drink> drinkRepository, TableRepository<Table> tableRepository) {
        this.foodRepository = foodRepository;
        this.drinkRepository = drinkRepository;
        this.tableRepository = tableRepository;
    }

    @Override
    public String addFood(String type, String name, double price) {
        BakedFood byName = this.foodRepository.getByName(name);
        if (byName != null) { //вече е добавена
            throw new IllegalArgumentException(String.format(FOOD_OR_DRINK_EXIST, type, name));
        }

        BakedFood bakedFood = null;
        if (type.equals("Bread")) {
            bakedFood = new Bread(name, price);
        } else if (type.equals("Cake")) {
            bakedFood = new Cake(name, price);
        }
        this.foodRepository.add(bakedFood);
        return String.format(FOOD_ADDED, name, type);
    }

    @Override
    public String addDrink(String type, String name, int portion, String brand) {
        Drink byNameAndBrand = this.drinkRepository.getByNameAndBrand(name, brand);
        if (byNameAndBrand != null) { //вече е добавена
            throw new IllegalArgumentException(String.format(FOOD_OR_DRINK_EXIST, type, name));
        }

        Drink drink = null;
        if (type.equals("Tea")) {
            drink = new Tea(name, portion, brand);
        } else if (type.equals("Water")) {
            drink = new Water(name, portion, brand);
        }
        this.drinkRepository.add(drink);
        return String.format(DRINK_ADDED, name, brand);
    }

    @Override
    public String addTable(String type, int tableNumber, int capacity) {
        Table byNumber = this.tableRepository.getByNumber(tableNumber);
        if (byNumber != null) { //вече е добавена
            throw new IllegalArgumentException(String.format(TABLE_EXIST, tableNumber));
        }

        Table table = null;
        if (type.equals("InsideTable")) {
            table = new InsideTable(tableNumber, capacity);
        } else if (type.equals("OutsideTable")) {
            table = new OutsideTable(tableNumber, capacity);
        }
        this.tableRepository.add(table);
        return String.format(TABLE_ADDED, tableNumber);
    }

    @Override
    public String reserveTable(int numberOfPeople) {
        Table table = this.tableRepository.getAll().stream()
                .filter(t -> !t.isReserved() && t.getCapacity() >= numberOfPeople)
                .findFirst()
                .orElse(null);
        if (table == null) {
            return String.format(RESERVATION_NOT_POSSIBLE, numberOfPeople);
        }
        table.reserve(numberOfPeople);
        return String.format(TABLE_RESERVED, table.getTableNumber(), numberOfPeople);
    }

    @Override
    public String orderFood(int tableNumber, String foodName) {
        Table tableByNumber = this.tableRepository.getByNumber(tableNumber);
        if (tableByNumber == null) {
            return String.format(WRONG_TABLE_NUMBER, tableNumber);
        }
        BakedFood foodByName = this.foodRepository.getByName(foodName);
        if (foodByName == null) {
            return String.format(NONE_EXISTENT_FOOD, foodName);
        }

        tableByNumber.orderFood(foodByName);
        return String.format(FOOD_ORDER_SUCCESSFUL, tableNumber, foodName);
    }

    @Override
    public String orderDrink(int tableNumber, String drinkName, String drinkBrand) {
        Table tableByNumber = this.tableRepository.getByNumber(tableNumber);
        if (tableByNumber == null) {
            return String.format(WRONG_TABLE_NUMBER, tableNumber);
        }

        Drink drinkByNameAndBrand = this.drinkRepository.getByNameAndBrand(drinkName, drinkBrand);
        if (drinkByNameAndBrand == null) {
            return String.format(NON_EXISTENT_DRINK, drinkName, drinkBrand);
        }

        tableByNumber.orderDrink(drinkByNameAndBrand);
        return String.format(DRINK_ORDER_SUCCESSFUL, tableNumber, drinkName, drinkBrand);


    }

    @Override
    public String leaveTable(int tableNumber) {
        Table tableByNumber = this.tableRepository.getByNumber(tableNumber);
        double bill = tableByNumber.getBill();
        tableByNumber.clear();
        this.totalIncome += bill;
        return String.format(BILL, tableNumber, bill);
    }

    @Override
    public String getFreeTablesInfo() {
        StringBuilder sb = new StringBuilder();

        this.tableRepository.getAll().stream()
                .filter(t -> !t.isReserved())
                .forEach(t -> sb.append(t.getFreeTableInfo()).append(System.lineSeparator()));

        return sb.toString().trim();
    }

    @Override
    public String getTotalIncome() {
        return String.format(TOTAL_INCOME, this.totalIncome);
    }
}
