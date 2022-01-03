package T08_ExceptionsAndErrorHandling.custom_exception;

public class Main {
    public static void main(String[] args) {
        try {
            Student student = new Student("4avdar", "test@test.com");
        } catch (InvalidPersonNameException e) {
            System.out.println(e.getMessage());
        }
    }
}
