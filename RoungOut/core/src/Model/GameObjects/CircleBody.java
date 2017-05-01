package Model.GameObjects;

/**
 * @author Ken Bäcklund
 */
public class CircleBody implements Body {

    private Location location;
    private CircleShape shape;

    // Constructors ///////////////////////////////////////////////////////////
    public CircleBody(double xPos, double yPos, double radius, double angle, double speed) {
        location = new Location(xPos, yPos, angle, speed);
        shape = new CircleShape(radius);
    }

    public CircleBody(double xPos, double yPos, double radius) {
        this(xPos, yPos, radius, 0f, 0f);
    }

    public CircleBody(double radius) {
        this(0f, 0f, radius, 0f, 0f);
    }

    public CircleBody(CircleBody c) {
        this(c.getX(), c.getY(), c.getRadius(), c.getAngle(), c.getSpeed());
    }

    // Getters ////////////////////////////////////////////////////////////////
    public double getX() {
        return location.getX();
    }

    public double getY() {
        return location.getY();
    }

    public double getAngle() {
        return location.getAngle();
    }

    public double getSpeed() {
        return location.getSpeed();
    }

    public double getRadius() {
        return shape.getRadius();
    }



    // Setters ////////////////////////////////////////////////////////////////
    public void setX(double xPos) {
        location.setX(xPos);
    }

    public void setY(double yPos) {
        location.setY(yPos);
    }

    public void setPosition(double xPos, double yPos) {
        location.setPosition(xPos, yPos);
    }

    public void setAngle(double radians) {
        location.setAngle(radians);
    }

    public void setSpeed(double speed) {
        location.setSpeed(speed);
    }

    public void setRadius(double radius) {
        shape = new CircleShape(radius);
    }

    public void setMaxSpeed(double maxSpeed) {
        location.setMaxSpeed(maxSpeed);
    }

    // Other methods //////////////////////////////////////////////////////////
    public double distance(double xPos, double yPos) {
        return Math.max(0, location.distance(xPos, yPos) - getRadius());
    }

    public double distance(Body body) {
        double dCenterPoints = location.distance(body.getX(), body.getY());
        double dToOther = distance(body.getX(), body.getY());
        double dFromOther = body.distance(getX(), getY());
        return Math.max(0, (dToOther + dFromOther)- dCenterPoints);
    }

    public void move() {
        location.move();
    }
}
