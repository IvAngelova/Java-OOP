package T04_InterfacesAndAbstraction.exercise.collection_hierarchy;

public class AddCollection extends Collection implements Addable {
    public AddCollection() {
        super();
    }

    @Override
    public int add(String string) {
        super.getItems().add(string);
        return super.getItems().indexOf(string);
    }
}
