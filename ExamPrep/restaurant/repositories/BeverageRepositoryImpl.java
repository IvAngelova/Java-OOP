package restaurant.repositories;

import restaurant.entities.drinks.interfaces.Beverages;
import restaurant.repositories.interfaces.BeverageRepository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class BeverageRepositoryImpl implements BeverageRepository<Beverages> {
    private List<Beverages> entities;

    public BeverageRepositoryImpl() {
        this.entities = new ArrayList<>();
    }

    @Override
    public Beverages beverageByName(String drinkName, String drinkBrand) {
        return this.entities.stream()
                .filter(b -> b.getName().equals(drinkName) && b.getBrand().equals(drinkBrand))
                .findFirst()
                .orElse(null);
    }

    @Override
    public List<Beverages> getAllEntities() {
        return Collections.unmodifiableList(this.entities);
    }

    @Override
    public void add(Beverages entity) {
        this.entities.add(entity);
    }
}
