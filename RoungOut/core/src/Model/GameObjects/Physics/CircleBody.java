package Model.GameObjects.Physics;

/**
 * @author Ken Bäcklund
 */
public class CircleBody implements Body {

    private Location location;
    private CircleShape shape;
    private float radius;

    // Constructors ///////////////////////////////////////////////////////////
    public CircleBody(float xPos, float yPos, float radius, float angle, float speed) {
        location = new Location(xPos, yPos, angle, speed);
        shape = new CircleShape(radius);
    }

    public CircleBody(float xPos, float yPos, float radius) {
        this(xPos, yPos, radius, 0f, 0f);
    }

    public CircleBody(float radius) {
        this(0f, 0f, radius, 0f, 0f);
    }

    public CircleBody(CircleBody c) {
        this(c.getX(), c.getY(), c.getRadius(), c.getAngle(), c.getSpeed());
    }
    // Getters ////////////////////////////////////////////////////////////////
    @Override
    public float getX() {
        return location.getX();
    }

    @Override
    public float getY() {
        return location.getY();
    }

    @Override
    public float getAngle() {
        return location.getAngle();
    }

    @Override
    public float getSpeed() {
        return location.getSpeed();
    }

    @Override
    public float getWidth() {
        return radius*2;
    }

    @Override
    public float getHeight() {
        return radius*2;
    }

    @Override
    public Location getLoc() {return location;}

    //Used here (dunno if its only here or used somewhere else)
    float getRadius(){return radius;}

    // Setters ////////////////////////////////////////////////////////////////
    @Override
    public void setX(float xPos) {
        location.setX(xPos);
    }

    @Override
    public void setY(float yPos) {
        location.setY(yPos);
    }

    @Override
    public void setPosition(float xPos, float yPos) {
        location.setPosition(xPos, yPos);
    }

    @Override
    public void setAngle(float radians) {
        location.setAngle(radians);
    }

    @Override
    public void setSpeed(float speed) {
        location.setSpeed(speed);
    }

    @Override
    public void setWidth(float width) {
        this.radius=radius*2;
    }

    @Override
    public void setHeight(float width) {
        this.radius=radius*2;
    }

    public void setMaxSpeed(float maxSpeed) {
        location.setMaxSpeed(maxSpeed);
    }

    // Other methods //////////////////////////////////////////////////////////
    public float distance(float xPos, float yPos) {
        return (float) Math.max(0, location.distance(xPos, yPos) - getRadius());
    }

    public float distance(Body body) {
        double dCenterPoints = location.distance(body.getX(), body.getY());
        double dToOther = distance(body.getX(), body.getY());
        double dFromOther = body.distance(getX(), getY());
        return (float) Math.max(0, (dToOther + dFromOther)- dCenterPoints);
    }

    public void move() {
        location.move();
    }
}
