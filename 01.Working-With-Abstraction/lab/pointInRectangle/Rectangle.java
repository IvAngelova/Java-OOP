package T01_WorkingWithAbstraction.lab.pointInRectangle;

public class Rectangle {

    private final Point bottomLeft;
    private final Point topRight;

    public Rectangle(Point A, Point B) {
        this.bottomLeft = A;
        this.topRight = B;
    }

    public boolean contains(Point point) {
        boolean isInHorizontal = bottomLeft.getX() <= point.getX() && topRight.getX() >= point.getX();
        boolean isInVertical = bottomLeft.getY() <= point.getY() && topRight.getY() >= point.getY();
        return isInHorizontal && isInVertical;
    }


}
