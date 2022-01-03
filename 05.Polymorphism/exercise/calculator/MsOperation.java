package T05_Polymorphism.exercise.calculator;

import java.util.Deque;

public class MsOperation implements Operation {

    private Deque<Integer> memory;


    public MsOperation(Deque<Integer> memory) {
        this.memory = memory;
    }

    @Override
    public void addOperand(int operand) {
        this.memory.push(operand);
    }

    @Override
    public int getResult() {
        return this.memory.peek();
    }

    @Override
    public boolean isCompleted() {
        return false;
    }
}
