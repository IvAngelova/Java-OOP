package T05_Polymorphism.lab.shapes;

public class Rectangle extends Shape {
    private Double height;
    private Double width;

    public Rectangle(Double height, Double width) {
        this.setHeight(height);
        this.setWidth(width);
        this.calculateArea();
        this.calculatePerimeter();
    }

    public Double getHeight() {
        return height;
    }

    public Double getWidth() {
        return width;
    }

    private void setHeight(Double height) {
        this.height = height;
    }

    private void setWidth(Double width) {
        this.width = width;
    }

    @Override
    protected void calculatePerimeter() {
        Double result = 2 * (this.height + this.width);
        super.setPerimeter(result);
    }

    @Override
    protected void calculateArea() {
        Double result =  this.width * this.height;
        super.setArea(result);
    }
}
