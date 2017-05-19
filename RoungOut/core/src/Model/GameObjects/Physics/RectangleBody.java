package Model.GameObjects.Physics;

/**
 * @author Ken Bäcklund
 * Modified by Alex 2017-05-07
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
        this.height = height;

        // shape = new RectangleShape(width, height);
    }

    public RectangleBody(float xPos, float yPos, float width, float height) {
        location = new Location(xPos,yPos,0,0);
        this.width=width;
        this.height=height;
    }

    public RectangleBody(float width, float height) {
        location = new Location(0f, 0f , 0f, 0f);
        this.width=width;
        this.height=height;
    }

    public RectangleBody(RectangleBody r) {
        location = r.getLoc();
        this.width=r.getWidth();
        this.height=r.getHeight();
    }

    // Private helper methods /////////////////////////////////////////////////

    private float lineDistance(float lineCenterPoint, float otherPoint, float lineSize) {
        // Get the distance from the center of a line segment towards another point.
        return Math.max(0, Math.abs(lineCenterPoint - otherPoint) - lineSize/2f);
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

    public float getDeltaX() {
        return location.getDeltaX();
    }

    public float getDeltaY() {
        return location.getDeltaY();
    }

    @Override
    public float getWidth() {
        return this.width;
    }

    @Override
    public float getHeight() {
        return this.height;
    }

    @Override
    public float getAngle() {
        return location.getAngle();
    }

    @Override
    public float getSpeed() {
        return location.getSpeed();
    }

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
    public void setWidth(float width) {
        this.width = width;
    }

    @Override
    public void setHeight(float height) {
        this.height = height;
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
    public void setMaxSpeed(float maxSpeed) {
        location.setMaxSpeed(maxSpeed);
    }


    @Override
    public Location getLoc() {
        return location;
    }

    // Other methods //////////////////////////////////////////////////////////
    @Override
    public float distance(Body body) {
        float dCenterPoints = location.distance(body.getX(), body.getY());
        float dToOther = distance(body.getX(), body.getY());
        float dFromOther = body.distance(getX(), getY());
        return Math.max(0, (dToOther + dFromOther)- dCenterPoints);
    }

    @Override
    public float distance(float xPos, float yPos) {
        float dx = lineDistance(location.getX(), xPos, getWidth());
        float dy = lineDistance(location.getY(), yPos, getHeight());
        return (float) Math.sqrt(dx*dx+dy*dy);
    }

    @Override
    public void move(float deltaTime) {
        location.move(deltaTime);
    }
}
