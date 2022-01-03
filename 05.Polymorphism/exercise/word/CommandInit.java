package T05_Polymorphism.exercise.word;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.List;

public class CommandInit extends CommandImpl {
    private Deque<String> memory;

    public CommandInit(StringBuilder text) {
        super(text);
        this.memory = new ArrayDeque<>();
    }

    @Override
    protected List<Command> initCommands() {
        var list = super.initCommands();
        list.add(new Command("cut", new CutTransform(memory)));
        list.add(new Command("paste", new PasteTransform(memory)));

        return list;
    }
}
