package T03_Inheritance.lab.stack_of_strings;

import java.util.ArrayList;
import java.util.List;

public class StackOfStrings {
    private List<String> data;

    public StackOfStrings() {
        this.data = new ArrayList<>();
    }

    public boolean isEmpty() {
        return this.data.isEmpty();
    }

    public String peek() {
        if(!this.data.isEmpty()) {
            return this.data.get(this.data.size() - 1);
        }
        return null;
    }

    public String pop() {
        if(!this.data.isEmpty()) {
            return this.data.remove(this.data.size() - 1);
        }
        return null;
    }

    public void push(String item) {
        this.data.add(item);
    }
}
