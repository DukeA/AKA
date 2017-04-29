package Model.GameObjects;

/**
 * @author Ken Bäcklund.
 */
public class Location {

    // Get/Set values
    private double xPos;
    private double yPos;
    private double speed;
    private double maxSpeed;
    private double angle;

    // Get only values
    private double deltaX;
    private double deltaY;

    public Location(double xPos, double yPos, double angle, double speed, double maxSpeed) {
        setMaxSpeed(maxSpeed);
        setPosition(xPos, yPos);
        setAngle(angle);
        setSpeed(speed);
    }

    public Location(double xPos, double yPos, double angle, double speed) {
        this(xPos, yPos, angle, speed, Double.MAX_VALUE);
    }

    public Location(Location loc) {
        this(loc.getX(), loc.getY(), loc.getAngle(), loc.getSpeed(), loc.getMaxSpeed());
    }

    // Location(double xPos, double yPos) { this(xPos, yPos, 0f, 0f); }

    // Helper methods /////////////////////////////////////////////////////////
    private void updateDeltaValues() {
        deltaX = Math.cos(angle) * speed;
        deltaY = Math.sin(angle) * speed;
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
    public double getX() {
        return xPos;
    }

    public double getY() {
        return yPos;
    }

    public double getSpeed() {
        return speed;
    }

    public double getAngle() {
        return angle;
    }

    public double getDeltaX() {
        return deltaX;
    }

    public double getDeltaY() {
        return deltaY;
    }

    public double getMaxSpeed() {
        return maxSpeed;
    }

    // Setters ////////////////////////////////////////////////////////////////
    public void setX(double xPos) {
        this.xPos = xPos;
    }

    public void setY(double yPos) {
        this.yPos = yPos;
    }

    public void setPosition(double xPos, double yPos) {
        this.xPos = xPos;
        this.yPos = yPos;
    }

    public void setSpeed(double speed) {
        this.speed = speed;     // Negative speed OK
        enforceSpeed();
        updateDeltaValues();
    }

    public void setMaxSpeed(double maxSpeed) {
        if (maxSpeed < 0) {
            throw new IllegalArgumentException("Max speed cannot be less than zero.");
        }
        this.maxSpeed = maxSpeed;
        enforceSpeed();
    }

    public void setAngle(double radians) {
        this.angle = radians;
        limitAngle();
        updateDeltaValues();
    }

    // Other methods //////////////////////////////////////////////////////////

    public void move() {
        xPos += deltaX;
        yPos += deltaY;
    }

    public double distance(double relXPos, double relYPos) {
        double dx = xPos - relXPos;
        double dy = yPos - relYPos;
        return Math.sqrt(dx*dx+dy*dy);
    }

    public double distance(Location location) {
        return distance(location.getX(), location.getY());
    }

}
