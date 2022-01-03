package T08_ExceptionsAndErrorHandling.valid_person;

public class Main {
    public static void main(String[] args) {
        try {
            createPerson("Pesho", "Petrov", 121);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void createPerson(String firstName, String lastName, int age) {
        Person p = new Person(firstName, lastName, age);
    }
}
