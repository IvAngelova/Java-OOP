package onlineShop.core;

import onlineShop.core.interfaces.Controller;
import onlineShop.models.products.Product;
import onlineShop.models.products.components.*;
import onlineShop.models.products.computers.Computer;
import onlineShop.models.products.computers.DesktopComputer;
import onlineShop.models.products.computers.Laptop;
import onlineShop.models.products.peripherals.*;

import java.util.*;
import java.util.stream.Collectors;

import static onlineShop.common.constants.ExceptionMessages.*;
import static onlineShop.common.constants.OutputMessages.*;

public class ControllerImpl implements Controller {

    private Map<Integer, Computer> computerRepo;
    private Map<Integer, Peripheral> peripheralRepo;
    private Map<Integer, Component> componentRepo;

    public ControllerImpl() {
        this.computerRepo = new LinkedHashMap<>();
        this.peripheralRepo = new LinkedHashMap<>();
        this.componentRepo = new LinkedHashMap<>();
    }

    @Override
    public String addComputer(String computerType, int id, String manufacturer, String model, double price) {
        Computer computerById = this.computerRepo.get(id);
        if (computerById != null) {
            throw new IllegalArgumentException(EXISTING_COMPUTER_ID);
        }
        Computer computer;
        if (computerType.equals("Laptop")) {
            computer = new Laptop(id, manufacturer, model, price);
        } else if (computerType.equals("DesktopComputer")) {
            computer = new DesktopComputer(id, manufacturer, model, price);
        } else {
            throw new IllegalArgumentException(INVALID_COMPUTER_TYPE);
        }
        this.computerRepo.put(id, computer);
        return String.format(ADDED_COMPUTER, id);
    }

    @Override
    public String addPeripheral(int computerId, int id, String peripheralType, String manufacturer, String model, double price, double overallPerformance, String connectionType) {
        if (!existComputer(computerId)) {
            throw new IllegalArgumentException(NOT_EXISTING_COMPUTER_ID);
        }
        if (this.peripheralRepo.containsKey(id)) {
            throw new IllegalArgumentException(EXISTING_PERIPHERAL_ID);
        }
        Peripheral peripheral;
        if (peripheralType.equals("Headset")) {
            peripheral = new Headset(id, manufacturer, model, price, overallPerformance, connectionType);
        } else if (peripheralType.equals("Keyboard")) {
            peripheral = new Keyboard(id, manufacturer, model, price, overallPerformance, connectionType);
        } else if (peripheralType.equals("Monitor")) {
            peripheral = new Monitor(id, manufacturer, model, price, overallPerformance, connectionType);
        } else if (peripheralType.equals("Mouse")) {
            peripheral = new Mouse(id, manufacturer, model, price, overallPerformance, connectionType);
        } else {
            throw new IllegalArgumentException(INVALID_PERIPHERAL_TYPE);
        }

        Computer computer = this.computerRepo.get(computerId);
        computer.addPeripheral(peripheral);
        this.peripheralRepo.put(id, peripheral);
        return String.format(ADDED_PERIPHERAL, peripheralType, id, computerId);

    }

    @Override
    public String removePeripheral(String peripheralType, int computerId) {
        if (!existComputer(computerId)) {
            throw new IllegalArgumentException(NOT_EXISTING_COMPUTER_ID);
        }
        Computer computer = this.computerRepo.get(computerId);
        Peripheral peripheralRemoved = computer.removePeripheral(peripheralType);
        this.peripheralRepo.remove(peripheralRemoved.getId());

        return String.format(REMOVED_PERIPHERAL, peripheralType, peripheralRemoved.getId());
    }

    @Override
    public String addComponent(int computerId, int id, String componentType, String manufacturer, String model, double price, double overallPerformance, int generation) {
        if (!existComputer(computerId)) {
            throw new IllegalArgumentException(NOT_EXISTING_COMPUTER_ID);
        }
        if (this.componentRepo.containsKey(id)) {
            throw new IllegalArgumentException(EXISTING_COMPONENT_ID);
        }
        Component component;
        if (componentType.equals("CentralProcessingUnit")) {
            component = new CentralProcessingUnit(id, manufacturer, model, price, overallPerformance, generation);
        } else if (componentType.equals("Motherboard")) {
            component = new Motherboard(id, manufacturer, model, price, overallPerformance, generation);
        } else if (componentType.equals("PowerSupply")) {
            component = new PowerSupply(id, manufacturer, model, price, overallPerformance, generation);
        } else if (componentType.equals("RandomAccessMemory")) {
            component = new RandomAccessMemory(id, manufacturer, model, price, overallPerformance, generation);
        } else if (componentType.equals("SolidStateDrive")) {
            component = new SolidStateDrive(id, manufacturer, model, price, overallPerformance, generation);
        } else if (componentType.equals("VideoCard")) {
            component = new VideoCard(id, manufacturer, model, price, overallPerformance, generation);
        } else {
            throw new IllegalArgumentException(INVALID_COMPONENT_TYPE);
        }

        Computer computer = this.computerRepo.get(computerId);
        computer.addComponent(component);
        this.componentRepo.put(id, component);
        return String.format(ADDED_COMPONENT, componentType, id, computerId);
    }

    @Override
    public String removeComponent(String componentType, int computerId) {
        if (!existComputer(computerId)) {
            throw new IllegalArgumentException(NOT_EXISTING_COMPUTER_ID);
        }
        Computer computer = this.computerRepo.get(computerId);
        Component componentRemoved = computer.removeComponent(componentType);
        this.componentRepo.remove(componentRemoved.getId());

        return String.format(REMOVED_COMPONENT, componentType, componentRemoved.getId());
    }

    @Override
    public String buyComputer(int id) {
        if (!existComputer(id)) {
            throw new IllegalArgumentException(NOT_EXISTING_COMPUTER_ID);
        }

        Computer removedComputer = this.computerRepo.remove(id);

        return removedComputer.toString();

    }

    @Override
    public String BuyBestComputer(double budget) {
        List<Computer> computers = this.computerRepo.values().stream()
                .filter(c -> c.getPrice() <= budget)
                .collect(Collectors.toList());

        List<Computer> endSelection = computers.stream()
                .sorted(Comparator.comparingDouble(Product::getOverallPerformance).reversed())
                .limit(1)
                .collect(Collectors.toList());

        if (endSelection.isEmpty()) {
            throw new IllegalArgumentException(String.format(CAN_NOT_BUY_COMPUTER, budget));
        }

        this.computerRepo.remove(endSelection.get(0).getId());

        return endSelection.get(0).toString();
    }

    @Override
    public String getComputerData(int id) {
        if (!existComputer(id)) {
            throw new IllegalArgumentException(NOT_EXISTING_COMPUTER_ID);
        }

        return this.computerRepo.get(id).toString();
    }

    private boolean existComputer(int id) {
        return this.computerRepo.containsKey(id);
    }
}
