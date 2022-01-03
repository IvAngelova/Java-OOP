package T04_InterfacesAndAbstraction.exercise.collection_hierarchy;

public class MyListImpl extends Collection implements MyList {
    public MyListImpl() {
        super();
    }

    @Override
    public String remove() {
        return super.getItems().remove(0);

    }
    @Override
    public int getUsed() {
        return super.getItems().size();
    }

    @Override
    public int add(String string) {
        super.getItems().add(0, string);
        return 0;
    }
}
