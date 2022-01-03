package onlineShop.models.products.computers;

import onlineShop.models.products.BaseProduct;
import onlineShop.models.products.Product;
import onlineShop.models.products.components.Component;
import onlineShop.models.products.peripherals.Peripheral;

import java.util.ArrayList;
import java.util.List;

import static onlineShop.common.constants.ExceptionMessages.*;

public abstract class BaseComputer extends BaseProduct implements Computer {
    private List<Component> components;
    private List<Peripheral> peripherals;

    protected BaseComputer(int id, String manufacturer, String model, double price, double overallPerformance) {
        super(id, manufacturer, model, price, overallPerformance);
        this.components = new ArrayList<>();
        this.peripherals = new ArrayList<>();
    }

    @Override
    public double getOverallPerformance() {
        if (this.components.isEmpty()) {
            return super.getOverallPerformance();
        }
        double avgOverallPerformanceComponents = this.components.stream()
                .mapToDouble(Product::getOverallPerformance)
                .average()
                .orElse(0);
        return super.getOverallPerformance() + avgOverallPerformanceComponents;
    }

    @Override
    public double getPrice() {
        return super.getPrice() + this.components.stream().mapToDouble(Product::getPrice).sum()
                + this.peripherals.stream().mapToDouble(Product::getPrice).sum();
    }

    @Override
    public List<Component> getComponents() {
        return this.components;
    }

    @Override
    public List<Peripheral> getPeripherals() {
        return this.peripherals;
    }

    @Override
    public void addComponent(Component component) {
        String typeComponent = component.getClass().getSimpleName();
        if (this.components.contains(component)) {
            throw new IllegalArgumentException(String.format(EXISTING_COMPONENT,
                    typeComponent, this.getClass().getSimpleName(), this.getId()));
        }
        this.components.add(component);
    }

    @Override
    public Component removeComponent(String componentType) {
        Component component = this.components.stream()
                .filter(c -> c.getClass().getSimpleName().equals(componentType))
                .findFirst()
                .orElse(null);
        if (this.components.isEmpty() || component == null) {
            throw new IllegalArgumentException(String.format(NOT_EXISTING_COMPONENT,
                    componentType, this.getClass().getSimpleName(), this.getId()));
        }

        this.components.remove(component);
        return component;
    }

    @Override
    public void addPeripheral(Peripheral peripheral) {
        String typePeripheral = peripheral.getClass().getSimpleName();
        if (this.peripherals.contains(peripheral)) {
            throw new IllegalArgumentException(String.format(EXISTING_PERIPHERAL,
                    typePeripheral, this.getClass().getSimpleName(), this.getId()));
        }
        this.peripherals.add(peripheral);
    }

    @Override
    public Peripheral removePeripheral(String peripheralType) {
        Peripheral peripheral = this.peripherals.stream()
                .filter(p -> p.getClass().getSimpleName().equals(peripheralType))
                .findFirst()
                .orElse(null);
        if (this.peripherals.isEmpty() || peripheral == null) {
            throw new IllegalArgumentException(String.format(NOT_EXISTING_PERIPHERAL,
                    peripheralType, this.getClass().getSimpleName(), this.getId()));
        }

        this.peripherals.remove(peripheral);
        return peripheral;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("Overall Performance: %.2f. Price: %.2f - %s: %s %s (Id: %d)",
                this.getOverallPerformance(), this.getPrice(), this.getClass().getSimpleName(),
                this.getManufacturer(), this.getModel(), this.getId())).append(System.lineSeparator());
        sb.append(String.format(" Components (%d):", this.components.size())).append(System.lineSeparator());
        this.components.forEach(c -> sb.append("  ").append(c).append(System.lineSeparator()));
        sb.append(String.format(" Peripherals (%d); Average Overall Performance (%.2f):",
                this.peripherals.size(),
                this.peripherals.stream()
                        .mapToDouble(Product::getOverallPerformance)
                        .average()
                        .orElse(0.0)))
                .append(System.lineSeparator());
        this.peripherals.forEach(p -> sb.append("  ").append(p).append(System.lineSeparator()));
        return sb.toString().trim();
    }
}
