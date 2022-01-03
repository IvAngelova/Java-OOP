package T05_Polymorphism.lab.shapes;

public class Circle extends Shape {
    private Double radius;

    public Circle(Double radius) {
        this.setRadius(radius);
        this.calculateArea();
        this.calculatePerimeter();
    }

    private void setRadius(Double radius) {
        this.radius = radius;
    }

    public final Double getRadius() {
        return radius;
    }

    @Override
    protected void calculatePerimeter() {
        Double result = 2 * Math.PI * radius;
        super.setPerimeter(result);
    }

    @Override
    protected void calculateArea() {
        Double result = Math.PI * radius * radius;
        super.setArea(result);

    }
}
