package Model.GameObjects;

/**
 * @author Ken Bäcklund
 */
public class CircleShape implements Shape {

    private final double radius;

    public CircleShape(double radius) {
        this.radius = Math.abs(radius);
    }

    public CircleShape(CircleShape circleShape)
    {
        this.radius = circleShape.getRadius();
    }

    public double getWidth() {
        return radius * 2f;
    }

    public double getHeight() {
        return radius * 2f;
    }

    public double getRadius() {
        return radius;
    }
}
