package restaurant.entities.tables;

import restaurant.entities.drinks.interfaces.Beverages;
import restaurant.entities.healthyFoods.interfaces.HealthyFood;
import restaurant.entities.tables.interfaces.Table;

import java.util.ArrayList;
import java.util.List;

import static restaurant.common.ExceptionMessages.*;

public abstract class BaseTable implements Table {

    private List<HealthyFood> healthyFood;
    private List<Beverages> beverages;
    private int number;
    private int size;
    private int numberOfPeople;
    private double pricePerPerson;
    private boolean isReservedTable;
    private double allPeople;

    protected BaseTable(int number, int size, double pricePerPerson) {
        this.healthyFood = new ArrayList<>();
        this.beverages = new ArrayList<>();
        this.number = number;
        this.setSize(size);
        this.pricePerPerson = pricePerPerson;
    }

    private void setSize(int size) {
        if (size <= 0) {
            throw new IllegalArgumentException(INVALID_TABLE_SIZE);
        }
        this.size = size;
    }

    private void setNumberOfPeople(int numberOfPeople) {
        if (numberOfPeople < 0) {
            throw new IllegalArgumentException(INVALID_NUMBER_OF_PEOPLE);
        }
        this.numberOfPeople = numberOfPeople;
    }

    @Override
    public int getTableNumber() {
        return this.number;
    }

    @Override
    public int getSize() {
        return this.size;
    }

    @Override
    public int numberOfPeople() {
        return this.numberOfPeople;
    }

    @Override
    public double pricePerPerson() {
        return this.pricePerPerson;
    }

    @Override
    public boolean isReservedTable() {
        return this.isReservedTable;
    }

    @Override
    public double allPeople() {
        return this.allPeople;
    }

    @Override
    public void reserve(int numberOfPeople) {
        this.setNumberOfPeople(numberOfPeople);
        this.isReservedTable = true;
        this.allPeople = this.pricePerPerson * this.numberOfPeople;
    }

    @Override
    public void orderHealthy(HealthyFood food) {
        this.healthyFood.add(food);
    }

    @Override
    public void orderBeverages(Beverages beverages) {
        this.beverages.add(beverages);
    }

    @Override
    public double bill() {
        return this.healthyFood.stream().mapToDouble(HealthyFood::getPrice).sum()
                + this.beverages.stream().mapToDouble(Beverages::getPrice).sum()
                + this.allPeople;
    }

    @Override
    public void clear() {
        this.beverages.clear();
        this.healthyFood.clear();
        this.isReservedTable = false;
        this.setNumberOfPeople(0);
        this.allPeople = 0;
    }

    @Override
    public String tableInformation() {
        return String.format("Table - %d%n" +
                "Size - %d%n" +
                "Type - %s%n" +
                "All price - %.2f", this.number, this.size, this.getClass().getSimpleName(), this.pricePerPerson);
    }
}
