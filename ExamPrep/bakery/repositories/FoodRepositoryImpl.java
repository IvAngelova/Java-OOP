package bakery.repositories;

import bakery.entities.bakedFoods.interfaces.BakedFood;
import bakery.repositories.interfaces.FoodRepository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class FoodRepositoryImpl implements FoodRepository<BakedFood> {
    private List<BakedFood> models;

    public FoodRepositoryImpl() {
        this.models = new ArrayList<>();
    }

    @Override
    public BakedFood getByName(String name) {
        return this.models.stream()
                .filter(f -> f.getName().equals(name))
                .findFirst()
                .orElse(null);
    }

    @Override
    public List<BakedFood> getAll() {
        return Collections.unmodifiableList(this.models);
    }

    @Override
    public void add(BakedFood bakedFood) {
        this.models.add(bakedFood);
    }
}
