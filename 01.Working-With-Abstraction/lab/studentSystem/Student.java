package T01_WorkingWithAbstraction.lab.studentSystem;

public class Student {
    private String name;
    private int age;
    private double grade;
    private String commentary;

    public Student(String name, int age, double grade) {
        this.name = name;
        this.age = age;
        this.grade = grade;
        this.commentary = getCommentary();
    }

    private String getCommentary() {
        String comment = "";
        if (grade >= 5.00) {
            comment = "Excellent student.";
        } else if (grade >= 3.50) {
            comment = "Average student.";
        } else {
            comment = "Very nice T03_Inheritance.exercise.T04_InterfacesAndAbstraction.exercise.person.";
        }
        return comment;
    }

    @Override
    public String toString() {
        return String.format("%s is %s years old. %s", name, age, commentary);
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return this.age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public double getGrade() {
        return this.grade;
    }

    public void setGrade(double grade) {
        this.grade = grade;
    }
}
