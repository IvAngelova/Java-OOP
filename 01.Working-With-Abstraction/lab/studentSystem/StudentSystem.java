package T01_WorkingWithAbstraction.lab.studentSystem;

import java.util.HashMap;
import java.util.Map;


public class StudentSystem {

    private Map<String, Student> studentsByName;

    public StudentSystem() {
        this.studentsByName = new HashMap<>();
    }

    public void executeCommand(String[] tokens) {
        String commandName = tokens[0];
        String studentName = tokens[1];
        if (commandName.equals("Create")) {
            executeCreateCommand(tokens, studentName);
        } else if (commandName.equals("Show")) {
            executeShowCommand(studentName);
        }
    }

    private void executeShowCommand(String studentName) {
        if (!this.studentsByName.containsKey(studentName)) {
            return;
        }
        Student student = this.studentsByName.get(studentName);
        System.out.println(student);
    }

    private void executeCreateCommand(String[] tokens, String studentName) {
        int age = Integer.parseInt(tokens[2]);
        double grade = Double.parseDouble(tokens[3]);
        Student student = new Student(studentName, age, grade);
        this.studentsByName.putIfAbsent(studentName, student);
    }
}



