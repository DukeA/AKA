package Model.GameObjects.Physics;

/**
 * @author Ken Bäcklund
 * Modified by Alex 07-05-17
 */
public class CircleBody implements Body {

    private Location location;
    private float radius;

    // Constructors ///////////////////////////////////////////////////////////

    /**
     * Constructor
     * @param xPos Position on X-Axis.
     * @param yPos Position on Y-Axis
     * @param radius The radius of the circle
     * @param angle The angular direction of the circle (0 degrees = Right, as degrees increment the angle rotates counter-clockwise)
     * @param speed The speed of the circle
     */
    public CircleBody(float xPos, float yPos, float radius, float angle, float speed) {
        location = new Location(xPos, yPos, angle, speed);
        this.radius=radius;
    }

    /**
     * Constructor
     * @param xPos Position on X-Axis
     * @param yPos Position on Y-Axis
     * @param radius The radius of the circle
     */
    public CircleBody(float xPos, float yPos, float radius) {
        location = new Location(xPos, yPos, 0f, 0f);
        this.radius=radius;
    }

    /**
     * Constructor
     * @param radius The radius of the circle
     */
    public CircleBody(float radius) {
        location = new Location(0,0,0,0);
        this.radius = radius;
    }
    /**
     * Clone Constructor
     * @param c c is the circle that is to be cloned
     */
    public CircleBody(CircleBody c) {
        this(c.getX(), c.getY(), c.getRadius(), c.getDirection(), c.getSpeed());
    }
    // Getters ////////////////////////////////////////////////////////////////

    /**
     * Getter, gets X-position
     * @return X-Position
     */
    @Override
    public float getX() {
        return location.getX();
    }

    /**
     * Getter, gets Y-Pos
     * @return Y-Position
     */
    @Override
    public float getY() {
        return location.getY();
    }

    /**
     * Getter, gets the X-Pos the circle will have after the next move
     * @return predicted X-Pos
     */
    public float getDeltaX() {
        return location.getDeltaX();
    }

    /**
     * Getter, gets the Y-Pos the circle will have after the next move
     * @return predicted Y-Pos
     */
    public float getDeltaY() {
        return location.getDeltaY();
    }

    /**
     * Getter, returns the direction in form of an angle
     * @return Angle of direction
     */
    @Override
    public float getDirection() {
        return location.getAngle();
    }

    /**
     * Getter, returns speed of the circle
     * @return speed of the circle
     */
    @Override
    public float getSpeed() {
        return location.getSpeed();
    }

    /**
     * Getter, gets the width of the circle
     * @return the width of the circle
     */
    @Override
    public float getWidth() {
        return radius*2;
    }

    /**
     * Getter, gets the height of the circle
     * @return the height of the circle
     */
    @Override
    public float getHeight() {
        return radius*2;
    }

    /**
     * Getter, gets the location data
     * @return Location data
     */
    @Override
    public Location getLoc() {return location;}

    //Used here (dunno if its only here or used somewhere else)

    /**
     * Getter, gets the radius
     * @return returns the radius
     */
    public float getRadius(){return radius;}

    // Setters ////////////////////////////////////////////////////////////////

    /**
     * Setter, sets X-Position
     * @param xPos X-Position
     */
    @Override
    public void setX(float xPos) {
        location.setX(xPos);
    }

    /**
     * Setter, sets Y-Position
     * @param yPos Y-Position
     */
    @Override
    public void setY(float yPos) {
        location.setY(yPos);
    }

    /**
     * Setter, sets X&Y position
     * @param xPos X-Position
     * @param yPos Y-Position
     */
    @Override
    public void setPosition(float xPos, float yPos) {
        location.setPosition(xPos, yPos);
    }

    /**
     * Setter, sets the angle of direction
     * @param radians The angle
     */
    @Override
    public void setDirection(float radians) {
        location.setAngle(radians);
    }

    /**
     * Setter, sets the speed
     * @param speed The speed
     */
    @Override
    public void setSpeed(float speed) {
        location.setSpeed(speed);
    }

    /**
     * Setter, sets the width
     * @param width The width
     */
    @Override
    public void setWidth(float width) {
        this.radius=Math.abs(width/2);
    }

    /**
     * Setter, sets the height
     * @param height The height
     */
    @Override
    public void setHeight(float height) {
        this.radius=Math.abs(height/2);
    }

    /**
     * Setter, sets the maximum speed
     * @param maxSpeed The maximum speed
     */
    @Override
    public void setMaxSpeed(float maxSpeed) {
        location.setMaxSpeed(maxSpeed);
    }

    // Other methods //////////////////////////////////////////////////////////

    /**
     * Calculates the distance to the X&Y Pos (from the circle's point)
     * @param xPos The X-Position
     * @param yPos The Y-Position
     * @return The distance to that point
     */
    @Override
    public float distance(float xPos, float yPos) {
        return Math.max(0, location.distance(xPos, yPos) - getRadius());
    }

    /**
     * Calculated the distance to a body
     * @param body The Body
     * @return The distance to that body
     */
    @Override
    public float distance(Body body) {
        float dCenterPoints = location.distance(body.getX(), body.getY());
        float dToOther = distance(body.getX(), body.getY());
        float dFromOther = body.distance(this.getX(), this.getY());
        return Math.max(0, (dToOther + dFromOther)- dCenterPoints);
    }

    /**
     * Moves the circle with respect to it's speed and the time between the frames
     * @param deltaTime Time between the frames
     */
    @Override
    public void move(float deltaTime) {
        location.move(deltaTime);
    }
}
