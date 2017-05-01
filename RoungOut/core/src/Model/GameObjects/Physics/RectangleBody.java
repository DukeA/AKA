package Model.GameObjects.Physics;

/**
 * @author Ken Bäcklund
 */
public class RectangleBody implements Body {

    private Location location;
    private RectangleShape shape;

    // Constructors ///////////////////////////////////////////////////////////
    public RectangleBody(double xPos, double yPos, double width, double height, double angle, double speed) {
        location = new Location(xPos, yPos, angle, speed);
        shape = new RectangleShape(width, height);
    }

    public RectangleBody(double xPos, double yPos, double width, double height) {
        this(xPos, yPos, width, height, 0f, 0f);
    }

    public RectangleBody(double width, double height) {
        this(0f, 0f, width, height, 0f, 0f);
    }

    public RectangleBody(RectangleBody r) {
        this(r.getX(), r.getY(), r.getWidth(), r.getHeight(), r.getAngle(), r.getSpeed());
    }

    // Private helper methods /////////////////////////////////////////////////

    private double lineDistance(double lineCenterPoint, double otherPoint, double lineSize) {
        // Get the distance from the center of a line segment towards another point.
        return Math.max(0, Math.abs(lineCenterPoint - otherPoint) - lineSize/2f);
    }

    // Getters ////////////////////////////////////////////////////////////////
    public double getX() {
        return location.getX();
    }

    public double getY() {
        return location.getY();
    }

    public double getWidth() {
        return shape.getWidth();
    }

    public double getHeight() {
        return shape.getHeight();
    }

    public double getAngle() {
        return location.getAngle();
    }

    public double getSpeed() {
        return location.getSpeed();
    }



    // Setters ////////////////////////////////////////////////////////////////
    public void setX(double xPos) {
        location.setX(xPos);
    }

    public void setY(double yPos) {
        location.setY(yPos);
    }

    public void setSize(double width, double height) {
        shape = new RectangleShape(width, height);
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

    public void setMaxSpeed(double maxSpeed) {
        location.setMaxSpeed(maxSpeed);
    }



    // Other methods //////////////////////////////////////////////////////////
    public double distance(Body body) {
        double dCenterPoints = location.distance(body.getX(), body.getY());
        double dToOther = distance(body.getX(), body.getY());
        double dFromOther = body.distance(getX(), getY());
        return Math.max(0, (dToOther + dFromOther)- dCenterPoints);
    }

    public double distance(double xPos, double yPos) {
        double dx = lineDistance(location.getX(), xPos, shape.getWidth());
        double dy = lineDistance(location.getY(), yPos, shape.getHeight());
        return Math.sqrt(dx*dx+dy*dy);
    }

    public void move() {
        location.move();
    }
}
