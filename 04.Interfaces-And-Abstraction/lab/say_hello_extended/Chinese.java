package T04_InterfacesAndAbstraction.lab.say_hello_extended;

public class Chinese extends BasePerson{
    public Chinese(String name) {
        super(name);
    }

    @Override
    public String sayHello() {
        return "Djydjybydjy";
    }
}
