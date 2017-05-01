package Model.GameObjects.Physics;

/**
 * @author Ken Bäcklund
 */
public class RectangleShape implements Shape {

    private final double width;
    private final double height;

    public RectangleShape(double width, double height) {
        this.width = Math.abs(width);
        this.height = Math.abs(height);
    }

    public RectangleShape(RectangleShape rectangleShape)
    {
        this.width = rectangleShape.getWidth();
        this.height = rectangleShape.getHeight();
    }

    public double getWidth() {
        return this.width;
    }

    public double getHeight() {
        return this.height;
    }

}
