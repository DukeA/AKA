package Model.GameObjects.Physics;

/**
 * @author Ken Bäcklund
 * Modified by Alex 07-05-17
 */
public class CircleBody implements Body {

    private Location location;
    private float radius;

    // Constructors ///////////////////////////////////////////////////////////
    public CircleBody(float xPos, float yPos, float radius, float angle, float speed) {
        location = new Location(xPos, yPos, angle, speed);
        this.radius=radius;
    }

    public CircleBody(float xPos, float yPos, float radius) {
        location = new Location(xPos, yPos, 0f, 0f);
        this.radius=radius;
    }

    public CircleBody(float radius) {
        location = new Location(0,0,0,0);
        this.radius = radius;
    }

    //Clone constructor
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
    public float getRadius(){return radius;}

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
        this.radius=Math.abs(width/2);
    }

    @Override
    public void setHeight(float height) {
        this.radius=Math.abs(height/2);
    }

    @Override
    public void setMaxSpeed(float maxSpeed) {
        location.setMaxSpeed(maxSpeed);
    }

    // Other methods //////////////////////////////////////////////////////////
    @Override
    public float distance(float xPos, float yPos) {
        return Math.max(0, location.distance(xPos, yPos) - getRadius());
    }

    @Override
    public float distance(Body body) {
        float dCenterPoints = location.distance(body.getX(), body.getY());
        float dToOther = distance(body.getX(), body.getY());
        float dFromOther = body.distance(this.getX(), this.getY());
        return Math.max(0, (dToOther + dFromOther)- dCenterPoints);
    }

    @Override
    public void move() {
        location.move();
    }
}
