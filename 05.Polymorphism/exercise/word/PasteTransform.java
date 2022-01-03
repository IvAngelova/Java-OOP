package T05_Polymorphism.exercise.word;

import java.util.Deque;

public class PasteTransform implements TextTransform {
    private Deque<String> memory;

    public PasteTransform(Deque<String> memory) {
        this.memory = memory;
    }

    @Override
    public void invokeOn(StringBuilder text, int startIndex, int endIndex) {
        if (startIndex == endIndex) {
            text.insert(startIndex, memory.peek());
        } else {
            text.replace(startIndex, endIndex, memory.peek());
        }

    }
}
