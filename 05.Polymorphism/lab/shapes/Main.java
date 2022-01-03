package T05_Polymorphism.lab.shapes;

public class Main {
    public static void main(String[] args) {
        Shape circle = new Circle(3.00);
        Shape rectangle = new Rectangle(3d, 4d);

        System.out.println(circle.getArea());
        System.out.println(circle.getPerimeter());

        System.out.println(rectangle.getArea());
        System.out.println(rectangle.getPerimeter());
    }
}
