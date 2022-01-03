package T04_InterfacesAndAbstraction.exercise.collection_hierarchy;

public class AddRemoveCollection extends Collection implements AddRemovable{
    public AddRemoveCollection() {
        super();
    }

    @Override
    public String remove() {
        return super.getItems().remove(super.getItems().size() - 1);

    }

    @Override
    public int add(String string) {
        super.getItems().add(0, string);
        return 0;
    }
}
