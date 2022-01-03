package T03_Inheritance.exercise.animals;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        String animalType = scan.nextLine();
        while (!animalType.equals("Beast!")) {
            String[] animalInfo = scan.nextLine().split("\\s+");
            try {
                String name = animalInfo[0];
                int age = Integer.parseInt(animalInfo[1]);
                String gender = animalInfo[2];
                switch (animalType) {
                    case "Cat":
                        Cat cat = new Cat(name, age, gender);
                        System.out.println(cat.toString());
                        break;
                    case "Frog":
                        Frog frog = new Frog(name,age,gender);
                        System.out.println(frog.toString());
                        break;
                    case "Dog":
                        Dog dog = new Dog(name,age,gender);
                        System.out.println(dog.toString());
                        break;
                    case "Kitten":
                        Kitten kitten = new Kitten(name,age);
                        System.out.println(kitten.toString());
                        break;
                    case "Tomcat":
                        Tomcat tomcat = new Tomcat(name,age);
                        System.out.println(tomcat.toString());
                        break;
                }

            } catch (IllegalArgumentException ex) {
                System.out.println(ex.getMessage());
            }

            animalType = scan.nextLine();
        }
    }
}
