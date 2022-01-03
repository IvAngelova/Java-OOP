package T08_ExceptionsAndErrorHandling.custom_exception;

public class Student {
    private String name;
    private String email;

    public Student(String name, String email) {
        this.setName(name);
        this.email = email;
    }

    private void setName(String name) {
        for (char c : name.toCharArray()) {
            if (!Character.isLetter(c)) {
                throw new InvalidPersonNameException("Name must contain only letters");
            }
        }
        this.name = name;
    }
}
