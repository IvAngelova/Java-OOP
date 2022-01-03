package T01_WorkingWithAbstraction.exercise.greedy_times;

import java.util.HashMap;
import java.util.Map;

public class Bag {
    private long capacity;
    private long currentTotalQuantity;
    //gold
    private long gold;
    private boolean goldIsAdded;
    //gems
    private Map<String, Long> gems;
    //cash
    private Map<String, Long> cash;

    public Bag(long capacity) {
        this.capacity = capacity;
        this.gems = new HashMap<>();
        this.cash = new HashMap<>();
        this.goldIsAdded = false;
    }

    public void addCash(String item, long quantity) {
        long totalGems = getTotalGems();
        long totalCash = getTotalCash();
        if (hasFreeCapacity(quantity) && totalGems >= totalCash + quantity) {
            if (!this.cash.containsKey(item)) {
                this.cash.put(item, quantity);
            } else {
                this.cash.put(item, this.cash.get(item) + quantity);
            }
            this.currentTotalQuantity += quantity;
        }
    }

    public void addGems(String item, long quantity) {
        long totalGems = getTotalGems();
        if (hasFreeCapacity(quantity) && this.gold >= totalGems + quantity) {
            if (!this.gems.containsKey(item)) {
                this.gems.put(item, quantity);
            } else {
                this.gems.put(item, this.gems.get(item) + quantity);
            }
            this.currentTotalQuantity += quantity;
        }

    }

    public void addGold(long quantity) {
        if (hasFreeCapacity(quantity)) {
            this.gold += quantity;
            this.currentTotalQuantity += quantity;
            this.goldIsAdded = true;
        }
    }

    private boolean hasFreeCapacity(long quantity) {
        return currentTotalQuantity + quantity <= capacity;
    }

    private long getTotalCash() {
        return this.cash.values().stream().mapToLong(c -> c).sum();
    }

    private long getTotalGems() {
        return this.gems.values().stream().mapToLong(g -> g).sum();
    }

    public String getContent() {

        StringBuilder sb = new StringBuilder();
        if (this.goldIsAdded) {
            sb.append("<Gold> $").append(this.gold).append(System.lineSeparator());
            sb.append("##Gold - ").append(this.gold).append(System.lineSeparator());
        }

        if (this.gems.size() > 0) {
            sb.append("<Gem> $").append(this.getTotalGems()).append(System.lineSeparator());
            this.gems.entrySet().stream().sorted((f, s) -> {
                int result = s.getKey().compareTo(f.getKey());
                if (result == 0) {
                    result = f.getValue().compareTo(s.getValue());
                }
                return result;
            }).forEach(entry -> {
                sb.append("##")
                        .append(entry.getKey())
                        .append(" - ")
                        .append(entry.getValue())
                        .append(System.lineSeparator());
            });
        }

        if (this.cash.size() > 0) {
            sb.append("<Cash> $").append(this.getTotalCash()).append(System.lineSeparator());
            this.cash.entrySet().stream().sorted((f, s) -> {
                int result = s.getKey().compareTo(f.getKey());
                if (result == 0) {
                    result = f.getValue().compareTo(s.getValue());
                }
                return result;
            }).forEach(entry -> {
                sb.append("##")
                        .append(entry.getKey())
                        .append(" - ")
                        .append(entry.getValue())
                        .append(System.lineSeparator());
            });
        }
        return sb.toString();
    }
}
