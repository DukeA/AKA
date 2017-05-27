package Model.GameObjects.Physics;

/**
 * @author Ken Bäcklund.
 * Modified by Alex 2017-05-07
 */
public class Location {

    /**
     * Location contains the position of a body, but also contains other things such and speed and angle and
     * other useful information, it also does some simple arithmetic
     */

    // Get/Set values
    private float xPos;
    private float yPos;
    private float speed;
    private float maxSpeed;
    private float angle;

    // Get only values
    private float deltaX;
    private float deltaY;

    /**
     * Constructor
     * @param xPos Position on X-Axis.
     * @param yPos Position on Y-Axis
     * @param angle The angular direction of the circle (0 degrees = Right, as degrees increment the angle rotates counter-clockwise)
     * @param speed Speed of the point
     * @param maxSpeed The maximum speed of a point
     */
    public Location(float xPos, float yPos, float angle, float speed, float maxSpeed) {
        setMaxSpeed(maxSpeed);
        setPosition(xPos, yPos);
        setAngle(angle);
        setSpeed(speed);
    }

    /**
     * Constructor
     * @param xPos Position on X-Axis.
     * @param yPos Position on Y-Axis
     * @param angle The angular direction of the circle (0 degrees = Right, as degrees increment the angle rotates counter-clockwise)
     * @param speed Speed of the point
     */
    public Location(float xPos, float yPos, float angle, float speed) {
        this(xPos, yPos, angle, speed, Float.MAX_VALUE);
    }

    /**
     * Clone Constructor
     * @param loc Location that is to be cloned
     */
    public Location(Location loc) {
        this(loc.getX(), loc.getY(), loc.getAngle(), loc.getSpeed(), loc.getMaxSpeed());
    }

    // Location(double xPos, double yPos) { this(xPos, yPos, 0f, 0f); }

    // Helper methods /////////////////////////////////////////////////////////

    /**
     * Updates the predicted position
     */
    private void updateDeltaValues() {
        deltaX = (float) Math.cos(angle) * speed;
        deltaY = (float) Math.sin(angle) * speed;
        //Casting since Math. req double thus making the left side of the equation a double
    }

    /**
     * Help method, enforces speed
     */
    private void enforceSpeed() {     // Limit speed
        if (speed > maxSpeed) {
            speed = maxSpeed;
            updateDeltaValues();
        }
    }

    /**
     * Help method, limits the angle to the interval [0-360}
     */
    private void limitAngle() {     // Limit angle within (0 <= a < 2*PI)
        final double MAX_ANGLE = Math.PI * 2f;
        while (angle < 0) {
            angle += MAX_ANGLE;
        }
        angle %= MAX_ANGLE;
    }

    // Getters ////////////////////////////////////////////////////////////////
    /**
     * Getters for the following things:
     * X-Position
     * Y-Position
     * Speed
     * Angle
     * Delta X-Pos
     * Delta Y-Pos
     * Max Speed
     */

    public float getX() {
        return xPos;
    }

    public float getY() {
        return yPos;
    }

    public float getSpeed() {
        return speed;
    }

    public float getAngle() {
        return angle;
    }

    public float getDeltaX() {
        return deltaX;
    }

    public float getDeltaY() {
        return deltaY;
    }

    public float getMaxSpeed() {
        return maxSpeed;
    }

    // Setters ////////////////////////////////////////////////////////////////
    /**
     * Setters for the following things:
     * X-Position
     * Y-Position
     * Speed
     * Angle
     * Delta X-Pos
     * Delta Y-Pos
     * Max Speed
     */
    public void setX(float xPos) {
        this.xPos = xPos;
    }

    public void setY(float yPos) {
        this.yPos = yPos;
    }

    public void setPosition(float xPos, float yPos) {
        this.xPos = xPos;
        this.yPos = yPos;
    }

    public void setSpeed(float speed) {
        this.speed = speed;     // Negative speed OK
        enforceSpeed();
        updateDeltaValues();
    }

    public void setMaxSpeed(float maxSpeed) {
        if (maxSpeed < 0) {
            throw new IllegalArgumentException("Max speed cannot be less than zero.");
        }
        this.maxSpeed = maxSpeed;
        enforceSpeed();
    }

    public void setAngle(float radians) {
        this.angle = radians;
        limitAngle();
        updateDeltaValues();
    }

    // Other methods //////////////////////////////////////////////////////////

    /**
     * Moves the location along the delta prediction with respect to the frame speed
     * @param deltaTime time between frames
     */
    public void move(float deltaTime) {
        xPos += deltaTime * deltaX;
        yPos += deltaTime * deltaY;
    }

    /**
     * Calculates the distance between location and one other point using pythagorean-theorem
     * @param relXPos X-Pos of the point
     * @param relYPos Y-Pos of the point
     * @return Returns the distance btween the location and the point
     */
    public float distance(float relXPos, float relYPos) {
        float dx = xPos - relXPos;
        float dy = yPos - relYPos;
        return (float) Math.sqrt(dx*dx+dy*dy);
    }

    /**
     * Overloaded distance method, does the same but takes a location as in-parameter (instead of a point)
     * @param location The location to which distance is to be calculated to
     * @return Returns the distance between this location and another location.
     */
    public float distance(Location location) {
        return distance(location.getX(), location.getY());
    }

}
