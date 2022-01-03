package bakery.repositories;

import bakery.entities.drinks.interfaces.Drink;
import bakery.repositories.interfaces.DrinkRepository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class DrinkRepositoryImpl implements DrinkRepository<Drink> {
    private List<Drink> models;

    public DrinkRepositoryImpl() {
        this.models = new ArrayList<>();
    }

    @Override
    public Drink getByNameAndBrand(String drinkName, String drinkBrand) {
        return this.models.stream()
                .filter(d -> d.getName().equals(drinkName) && d.getBrand().equals(drinkBrand))
                .findFirst()
                .orElse(null);
    }

    @Override
    public List<Drink> getAll() {
        return Collections.unmodifiableList(this.models);
    }

    @Override
    public void add(Drink drink) {
        this.models.add(drink);
    }
}
