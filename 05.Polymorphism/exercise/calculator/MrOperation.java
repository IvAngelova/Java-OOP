package T05_Polymorphism.exercise.calculator;

import java.util.Deque;

public class MrOperation implements Operation {


    private Deque<Integer> memory;


    public MrOperation(Deque<Integer> memory) {
        this.memory = memory;
    }

    @Override
    public void addOperand(int operand) {

    }

    @Override
    public int getResult() {
        return this.memory.pop();
    }

    @Override
    public boolean isCompleted() {
        return !this.memory.isEmpty();
    }
}
