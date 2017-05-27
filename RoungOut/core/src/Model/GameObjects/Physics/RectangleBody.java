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
    /**
     * Constructor
     * @param xPos Position on X-Axis.
     * @param yPos Position on Y-Axis
     * @param width Width of the rectangle
     * @param height Height of the rectangle
     * @param angle The angular direction of the rectangle (0 degrees = Right, as degrees increment the angle rotates counter-clockwise)
     * @param speed The speed of the rectangle
     */
    public RectangleBody(float xPos, float yPos, float width, float height, float angle, float speed) {
        location = new Location(xPos, yPos, angle, speed);
        this.width=width;
        this.height = height;

        // shape = new RectangleShape(WIDTH, HEIGHT);
    }

    /**
     * Constructor
     * @param xPos Position on X-Axis.
     * @param yPos Position on Y-Axis
     * @param width Width of the rectangle
     * @param height Height of the rectangle
     */
    public RectangleBody(float xPos, float yPos, float width, float height) {
        location = new Location(xPos,yPos,0,0);
        this.width=width;
        this.height=height;
    }

    /**
     * Constructor
     * @param width Width of the rectangle
     * @param height Height of the rectangle
     */
    public RectangleBody(float width, float height) {
        location = new Location(0f, 0f , 0f, 0f);
        this.width=width;
        this.height=height;
    }

    /**
     * Clone Constructor
     * @param r r is the rectangle that is to be cloned
     */
    public RectangleBody(RectangleBody r) {
        location = r.getLoc();
        this.width=r.getWidth();
        this.height=r.getHeight();
    }

    // Private helper methods /////////////////////////////////////////////////

    /**
     *  Method get the distance from the center of a line segment towards another point.
     *
     * @param lineCenterPoint The center point of the line
     * @param otherPoint The other point on the line
     * @param lineSize Size of the line
     * @return Returns the distance beween the points
     */
    private float lineDistance(float lineCenterPoint, float otherPoint, float lineSize) {
        return Math.max(0, Math.abs(lineCenterPoint - otherPoint) - lineSize/2f);
    }

    // Getters ////////////////////////////////////////////////////////////////
    /**
     * Getters for the following things:
     * X-Position
     * Y-Position
     * Delta X-Pos
     * Delta Y-Pos
     * Width
     * Height
     * Direction (Angle of direction)
     * Speed
     */

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
    public float getDirection() {
        return location.getAngle();
    }

    @Override
    public float getSpeed() {
        return location.getSpeed();
    }

    // Setters ////////////////////////////////////////////////////////////////
    /**
     * Getters for the following things:
     * X-Position
     * Y-Position
     * Width
     * Height
     * X-Position & Y-Position
     * Direction (Angle of direction)
     * Speed
     * Maximum Speed
     * Location
     */

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
    public void setDirection(float radians) {
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

    /**
     * Calculated the distance to a body
     * @param body The Body
     * @return The distance to that body
     */
    @Override
    public float distance(Body body) {
        float dCenterPoints = location.distance(body.getX(), body.getY());
        float dToOther = distance(body.getX(), body.getY());
        float dFromOther = body.distance(getX(), getY());
        return Math.max(0, (dToOther + dFromOther)- dCenterPoints);
    }

    /**
     * Calculates the distance to the X&Y Pos (from the rectangle's point)
     * @param xPos The X-Position
     * @param yPos The Y-Position
     * @return The distance to that point
     */
    @Override
    public float distance(float xPos, float yPos) {
        float dx = lineDistance(location.getX(), xPos, getWidth());
        float dy = lineDistance(location.getY(), yPos, getHeight());
        return (float) Math.sqrt(dx*dx+dy*dy);
    }

    /**
     * Moves the rectangle with respect to it's speed and the time between the frames
     * @param deltaTime Time between the frames
     */
    @Override
    public void move(float deltaTime) {
        location.move(deltaTime);
    }
}
