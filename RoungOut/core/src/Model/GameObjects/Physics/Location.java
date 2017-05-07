package Model.GameObjects.Physics;

/**
 * @author Ken Bäcklund.
 */
public class Location {

    // Get/Set values
    private float xPos;
    private float yPos;
    private float speed;
    private float maxSpeed;
    private float angle;

    // Get only values
    private float deltaX;
    private float deltaY;

    public Location(float xPos, float yPos, float angle, float speed, float maxSpeed) {
        setMaxSpeed(maxSpeed);
        setPosition(xPos, yPos);
        setAngle(angle);
        setSpeed(speed);
    }
    public Location(float xPos, float yPos, float angle, float speed) {
        this(xPos, yPos, angle, speed, Float.MAX_VALUE);
    }

    public Location(Location loc) {
        this(loc.getX(), loc.getY(), loc.getAngle(), loc.getSpeed(), loc.getMaxSpeed());
    }

    // Location(double xPos, double yPos) { this(xPos, yPos, 0f, 0f); }

    // Helper methods /////////////////////////////////////////////////////////
    private void updateDeltaValues() {
        deltaX = (float) Math.cos(angle) * speed;
        deltaY = (float) Math.sin(angle) * speed;
        //Casting since Math. req double thus making the left side of the equation a double
    }

    private void enforceSpeed() {     // Limit speed
        if (speed > maxSpeed) {
            speed = maxSpeed;
            updateDeltaValues();
        }
    }

    private void limitAngle() {     // Limit angle within (0 <= a < 2*PI)
        final double MAX_ANGLE = Math.PI * 2f;
        while (angle < 0) {
            angle += MAX_ANGLE;
        }
        angle %= MAX_ANGLE;
    }

    // Getters ////////////////////////////////////////////////////////////////
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

    public void move() {
        xPos += deltaX;
        yPos += deltaY;
    }

    public float distance(float relXPos, float relYPos) {
        float dx = xPos - relXPos;
        float dy = yPos - relYPos;
        return (float) Math.sqrt(dx*dx+dy*dy);
    }

    public float distance(Location location) {
        return distance(location.getX(), location.getY());
    }

}
