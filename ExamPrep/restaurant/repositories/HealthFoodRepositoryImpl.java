package restaurant.repositories;

import restaurant.entities.healthyFoods.interfaces.HealthyFood;
import restaurant.repositories.interfaces.HealthFoodRepository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class HealthFoodRepositoryImpl implements HealthFoodRepository<HealthyFood> {
    private List<HealthyFood> entities;

    public HealthFoodRepositoryImpl() {
        this.entities = new ArrayList<>();
    }

    @Override
    public HealthyFood foodByName(String name) {
        return this.entities.stream().filter(f -> f.getName().equals(name))
                .findFirst()
                .orElse(null);
    }

    @Override
    public List<HealthyFood> getAllEntities() {
        return Collections.unmodifiableList(this.entities);
    }

    @Override
    public void add(HealthyFood entity) {
        this.entities.add(entity);
    }
}
