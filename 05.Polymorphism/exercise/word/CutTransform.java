package T05_Polymorphism.exercise.word;

import java.util.Deque;

public class CutTransform implements TextTransform {
    private Deque<String> memory;

    public CutTransform(Deque<String> memory) {
        this.memory = memory;
    }

    @Override
    public void invokeOn(StringBuilder text, int startIndex, int endIndex) {
        //this.memory.clear();

        String cutText = text.substring(startIndex, endIndex);

        if (!cutText.isEmpty()) {
            this.memory.push(cutText);
        }

        text.delete(startIndex, endIndex);

    }
}
