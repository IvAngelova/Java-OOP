package T09_DesignPatterns.strategy;

public class StrategyAdd implements Strategy{
    @Override
    public int doOperation(int num1, int num2) {
        return num1 + num2;
    }
}
