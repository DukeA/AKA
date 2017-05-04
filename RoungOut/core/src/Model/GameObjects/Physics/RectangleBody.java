package Model.GameObjects.Physics;

/**
 * @author Ken Bäcklund
 */
public class RectangleBody implements Body {

    private Location location;
    private float width;
    private float height;
    //private RectangleShape shape;

    // Constructors ///////////////////////////////////////////////////////////
    public RectangleBody(float xPos, float yPos, float width, float height, float angle, float speed) {
        location = new Location(xPos, yPos, angle, speed);
        this.width=width;

       // shape = new RectangleShape(width, height);
    }

    public RectangleBody(float xPos, float yPos, float width, float height) {
        this(xPos, yPos, width, height, 0, 0);
    }

    public RectangleBody(float width, float height) {
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
    public float getX() {
        return location.getX();
    }

    public float getY() {
        return location.getY();
    }

    public float getWidth() {
        return this.width;
    }

    public float getHeight() {
        return this.height;
    }

    public float getAngle() {
        return location.getAngle();
    }

    public float getSpeed() {
        return location.getSpeed();
    }



    // Setters ////////////////////////////////////////////////////////////////
    public void setX(float xPos) {
        location.setX(xPos);
    }

    public void setY(float yPos) {
        location.setY(yPos);
    }

    public void setSize(float newWidth, float newheight) {
        this.width = newWidth;
        this.height = newheight;
    }

    public void setPosition(float xPos, float yPos) {
        location.setPosition(xPos, yPos);
    }

    public void setAngle(float radians) {
        location.setAngle(radians);
    }

    public void setSpeed(float speed) {
        location.setSpeed(speed);
    }

    public void setMaxSpeed(float maxSpeed) {
        location.setMaxSpeed(maxSpeed);
    }


    @Override
    public Location getLoc() {
        return location;
    }

    // Other methods //////////////////////////////////////////////////////////
    public double distance(Body body) {
        float dCenterPoints = location.distance(body.getX(), body.getY());
        float dToOther = distance(body.getX(), body.getY());
        float dFromOther = body.distance(getX(), getY());
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
