package aquarium.entities.aquariums;

import aquarium.entities.decorations.Decoration;
import aquarium.entities.fish.Fish;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import static aquarium.common.ConstantMessages.*;
import static aquarium.common.ExceptionMessages.*;

public abstract class BaseAquarium implements Aquarium {
    private String name;
    private int capacity;
    private List<Decoration> decorations;
    private List<Fish> fish;

    protected BaseAquarium(String name, int capacity) {
        this.setName(name);
        this.capacity = capacity;
        this.decorations = new ArrayList<>();
        this.fish = new ArrayList<>();
    }

    protected void setName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new NullPointerException(AQUARIUM_NAME_NULL_OR_EMPTY);
        }
        this.name = name;
    }

    @Override
    public int calculateComfort() {
        return this.decorations.stream().mapToInt(Decoration::getComfort).sum();
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public void addFish(Fish fish) {
        if (this.fish.size() == this.capacity) {
            throw new IllegalStateException(NOT_ENOUGH_CAPACITY);
        }

        this.fish.add(fish);
    }

    @Override
    public void removeFish(Fish fish) {
        this.fish.remove(fish);
    }

    @Override
    public void addDecoration(Decoration decoration) {
        this.decorations.add(decoration);
    }

    @Override
    public void feed() {
        this.fish.forEach(Fish::eat);
    }

    @Override
    public String getInfo() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("%s (%s):", this.name, this.getClass().getSimpleName())).append(System.lineSeparator());
        sb.append("Fish: ");
        if (this.fish.isEmpty()) {
            sb.append("none").append(System.lineSeparator());
        } else {
            List<String> fishByName = this.fish.stream().map(Fish::getName).collect(Collectors.toList());
            sb.append(String.format("%s", String.join(" ", fishByName))).append(System.lineSeparator());
        }
        sb.append(String.format("Decorations: %d", this.decorations.size())).append(System.lineSeparator());
        sb.append(String.format("Comfort: %d", this.calculateComfort()));
        return sb.toString();

    }

    @Override
    public List<Fish> getFish() {
        return this.fish;
    }

    @Override
    public List<Decoration> getDecorations() {
        return this.decorations;
    }
}
