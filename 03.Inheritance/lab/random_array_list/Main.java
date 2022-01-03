package T03_Inheritance.lab.random_array_list;

public class Main {
    public static void main(String[] args) {

        RandomArrayList words = new RandomArrayList();

        words.add("First T05_Polymorphism.exercise.word");
        words.add("Second T05_Polymorphism.exercise.word");
        words.add("Third T05_Polymorphism.exercise.word");

        System.out.println(words.getRandomElement());
        System.out.println(words.getRandomElement());
        System.out.println(words.getRandomElement());
    }
}
