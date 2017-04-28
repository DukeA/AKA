package Model.GameObjects;

/**
 * Ball Class
 * Handles basic ball action
 *
 * @author Ken Bäcklund
 */
public class Ball {

    // PRIVATE VARIABLES //////////////////////////////////////////////////////

    // Ball's radius and current position, speed and direction.
    private double ballXPos;
    private double ballYPos;
    private double radius;      // Radius > 0.
    private double speed;       // Speed >= 0.
    private double degrees;     // Degrees 0-360.

    // Delta values, updated whenever speed or angle changes.
    private double ballDeltaX;
    private double ballDeltaY;



    // CONSTRUCTOR ////////////////////////////////////////////////////////////

    /**
     * Creates a Ball instance with a given position. No speed or direction.
     *
     * @param x - The ball's X position.
     * @param y - The ball's Y position.
     * @param radius - The ball's radius.
     */
    public Ball(double x, double y, double radius) {
        setPosition(x, y);
        this.radius = Math.abs(radius);     // No negative radius.
    }

    /**
     * Creates a Ball instance with a given position, speed and direction.
     *
     * @param x - The ball's X position.
     * @param y - The ball's Y position.
     * @param radius - The ball's radius.
     * @param degrees - The ball's angle in degrees, 0-360
     * @param speed - The ball's speed.
     */
    public Ball(double x, double y, double radius, double degrees, double speed) {
        this(x, y, radius);
        setDegrees(degrees);
        setSpeed(speed);
    }

    // PRIVATE METHODS ////////////////////////////////////////////////////////

    // Update the delta values based on ball's speed and direction.
    private void updateDeltaValues() {
        ballDeltaX = Math.cos( Math.toRadians(degrees) ) * speed;
        ballDeltaY = Math.sin( Math.toRadians(degrees) ) * speed;
    }

    // PUBLIC METHODS /////////////////////////////////////////////////////////

    /**
     * Get ball's current X position.
     * @return ball's current X position.
     */
    public double getX() {
        return ballXPos;
    }

    /**
     * Set ball's current Y position.
     * @param x - the ball's new X position.
     */
    public void setX(double x) {
        this.ballXPos = x;
    }

    /**
     * Get ball's current Y position.
     * @return - the ball's current Y position.
     */
    public double getY() {
        return ballYPos;
    }

    /**
     * Set ball's Y position.
     * @param y - ball's Y position.
     */
    public void setY(double y) {
        this.ballYPos = y;
    }

    /**
     * Set ball's position
     * @param x - ball's new X position.
     * @param y - ball's new Y position.
     */
    public void setPosition(double x, double y) {
        this.ballXPos = x;
        this.ballYPos = y;
    }

    /**
     * Get ball's radius.
     * @return - the ball's radius.
     */
    public double getRadius() {
        return radius;
    }

    /**
     * Set the ball's radius.
     * @param radius - the ball's radius.
     */
    public void setRadius(double radius) {
        this.radius = Math.abs(radius);
    }

    /**
     * Get the ball's current speed.
     * @return - the ball's current speed. (Always positive.)
     */
    public double getSpeed() {
        return speed;
    }

    /**
     * Set ball's speed.
     * @param speed - Ball's speed. A negative value will change to the opposite direction.
     */
    public void setSpeed(double speed) {
        // Speed is always positive, change opposite direction if a negative value was given.
        if (speed < 0) {
            speed *= -1;
            setDegrees(degrees + 180.0);
        }
        this.speed = speed;
        updateDeltaValues();
    }

    /**
     * Get the ball's current direction.
     * @return - the ball's angle in degrees 0-360.
     */
    public double getDegrees() {
        return degrees;
    }

    /**
     * Set ball's current angle in 0-360 degrees.
     * @param degrees
     */
    public void setDegrees(double degrees) {
        while (degrees < 360.0f) {
            degrees += 360.0f;
        }
        this.degrees = degrees % 360.0;
        updateDeltaValues();
    }

    /**
     * Get the ball's delta-X value
     * @return - the ball's delta-X value.
     */
    public double getDeltaX() {
        return ballDeltaX;
    }

    /**
     * Get the ball's delta-Y value
     * @return - the ball's delta-Y value.
     */
    public double getDeltaY() {
        return ballDeltaY;
    }

    /**
     * Move the ball to it's next position based on it's current direction and speed.
     */
    public void moveBall() {
        ballXPos += ballDeltaX;
        ballYPos += ballDeltaY;
    }


}
