package Model.GameObjects;

import Model.GameObjects.Physics.*;

import java.awt.*;

/**
 * @author Ken Bäcklund
 */
public class Brick implements  IBrick {

    public enum BrickType {
        NORMAL, DESTROYED, SPEED_UP_BALL, SLOW_DOWN_BALL
    }

    protected RectangleBody body;
    protected BrickType brickvalue;

    /**
     * Constructor
     * @param xPos Position on X-Axis.
     * @param yPos Position on Y-Axis
     * @param width Width of the rectangle
     * @param height Height of the rectangle
     * @param angle The angular direction of the rectangle (0 degrees = Right, as degrees increment the angle rotates counter-clockwise)
     * @param speed The speed of the rectangle
     */
    public Brick(float xPos, float yPos, float width, float height, float angle, float speed) {
        body = new RectangleBody(xPos, yPos, width, height, angle, speed);
        brickvalue = BrickType.NORMAL;
    }

    /**
     * Constructor
     * @param xPos Position on X-Axis.
     * @param yPos Position on Y-Axis
     * @param width Width of the rectangle
     * @param height Height of the rectangle
     */
    public Brick(float xPos, float yPos, float width, float height) {
        body = new RectangleBody(xPos, yPos, width, height, 0f, 0f);
        brickvalue = BrickType.NORMAL;
    }

    /**
     * Getters for the following values
     * Width
     * Height
     * BrickType
     * X-Position
     * Y-Position
     * Angle (Angle of direction)
     * Speed
     * Body
     */

    public float getWidth(){
        return body.getWidth();
    }
    public float getHeight() {
        return body.getHeight();
    }

    public BrickType getBrickType() {
        return brickvalue;
    }

    public float getX() {
        return body.getX();
    }

    public float getY() {
        return body.getY();
    }

    public RectangleBody getBody() {
        return body;
    }

    /**
     * Sets the BrickType to DESTROYRED
     */
    public void markDestroyed() {
        brickvalue = BrickType.DESTROYED;
    }

    /**
     * Checks if the BrickType is DESTROYRED
     * @return true if BrickType == DESTROYRED else false
     */
    public boolean isDestroyed() {
        return brickvalue == BrickType.DESTROYED;
    }

    /**
     * Setters for the following vales are:
     * X-Position
     * Y-Position
     * X&Y Position
     * Angle (Angle of direction)
     * Speed
     * Maximum Speed
     */

    public void setX(float xPos) {
        body.setX(xPos);
    }

    public void setY(float yPos) {
        body.setY(yPos);
    }

    public void setPosition(float xPos, float yPos) {
        body.setPosition(xPos, yPos);
    }

    /*public void setAngle(float radians) {
        body.setDirection(radians);
    }

    public void setSpeed(float speed) {
        body.setSpeed(speed);
    }

    public void setMaxSpeed(float maxSpeed) {
        body.setMaxSpeed(maxSpeed);
    }*/

    /**
     * Calculates distance from this brick to another body (using the method inside body)
     * @param otherBody The other body
     * @return The distance from this brick and the other body
     */
    public double distance(Body otherBody) {
        return body.distance(otherBody);
    }

    /**
     * Calculates distance from this brick to another point(X&Y-Position)(using the method inside body)
     * @param xPos X-Position
     * @param yPos Y-Position
     * @return The distance from this brick and the other body
     */
    public double distance(float xPos, float yPos) {
        return body.distance(xPos, yPos);
    }
}
