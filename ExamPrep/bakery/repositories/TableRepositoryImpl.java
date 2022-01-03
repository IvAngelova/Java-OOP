package bakery.repositories;

import bakery.entities.tables.interfaces.Table;
import bakery.repositories.interfaces.TableRepository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class TableRepositoryImpl implements TableRepository<Table> {
    private List<Table> models;

    public TableRepositoryImpl() {
        this.models = new ArrayList<>();
    }

    @Override
    public List<Table> getAll() {
        return Collections.unmodifiableList(this.models);
    }

    @Override
    public void add(Table table) {
        this.models.add(table);
    }

    @Override
    public Table getByNumber(int number) {
        return this.models.stream()
                .filter(t -> t.getTableNumber() == number)
                .findFirst()
                .orElse(null);
    }
}
