package viceCity.repositories.interfaces;

import java.util.Collection;
import java.util.List;

public interface Repository<T> {
    List<T> getModels();

    void add(T model);

    boolean remove(T model);

    T find(String name);
}
