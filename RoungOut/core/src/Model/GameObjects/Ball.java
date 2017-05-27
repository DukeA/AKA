package Model.GameObjects;

import Model.GameObjects.Physics.Body;
import Model.GameObjects.Physics.CircleBody;


/**
 * The ball class. It's basically a Facade pattern class to reduce coupling towards Physics package.
 * @author Ken Bäcklund
 * Modified by Alex 07-05-17
 */
public class Ball implements IBall, IMovable {

    // The CircleBody interface, which essentially is the ball.
    private CircleBody body;



    // Constructors ///////////////////////////////////////////////////////////

    /**
     * Create a new Ball.
     * @param xPos the X position where to place the ball.
     * @param yPos the Y position where to place the ball.
     * @param radius the ball radius.
     * @param angle the ball direction in radians. 0=right, PI=left.
     * @param speed the ball speed.
     */
    public Ball(float xPos, float yPos, float radius, float angle, float speed) {
        body = new CircleBody(xPos, yPos, radius, angle, speed);
        // Max speed 400% of original speed.
        setMaxSpeed(speed * 4);
    }

    /**
     * Create a new Ball without a speed or direction.
     * @param xPos the X position where to place the ball.
     * @param yPos the Y position where to place the ball.
     * @param radius the ball radius.
     */
    public Ball(float xPos, float yPos, float radius) {
        this(xPos, yPos, radius, 0f, 0f);
    }

    /**
     * Create a new Ball by cloning another ball instance.
     */
    public Ball(Ball ball) {
        this(ball.getX(), ball.getY(), ball.getRadius(), ball.getDirection(), ball.getSpeed());
    }



    // Getters ////////////////////////////////////////////////////////////////

    /**
     * Get the ball X position.
     * @return the ball X position.
     */
    public float getX() {
        return body.getX();
    }

    /**
     * Get the ball Y position.
     * @return the ball Y position.
     */
    public float getY() {
        return body.getY();
    }

    /**
     * Get the ball's horizontal speed.
     * @return the ball's horizontal speed. Positive = right direction.
     */
    public float getDeltaX() {
        return body.getDeltaX();
    }

    /**
     * Get the ball's vertical speed.
     * @return the ball's vertical speed. Positive = upward direction.
     */
    public float getDeltaY() {
        return body.getDeltaY();
    }

    /**
     * Get the ball's movement direction.
     * @return the ball movement direction in radians.
     */
    public float getDirection() {
        return body.getLoc().getDirection();
    }

    /**
     * Get the ball's speed.
     * @return the ball's speed.
     */
    public float getSpeed() {
        return body.getLoc().getSpeed();
    }

    /**
     * Get the ball's radius.
     * @return the ball's radius.
     */
    public float getRadius() {
        return body.getRadius();
    }



    // Setters ////////////////////////////////////////////////////////////////

    /**
     * Set the ball's X position.
     * @param xPos the ball's X position.
     */
    public void setX(float xPos) {
        body.setX(xPos);
    }

    /**
     * Set the ball's Y position.
     * @param yPos the ball's Y position.
     */
    public void setY(float yPos) {
        body.setY(yPos);
    }

    /**
     * Set the ball's position.
     * @param xPos the ball's X position.
     * @param yPos the ball's Y position.
     */
    public void setPosition(float xPos, float yPos) {
        body.setPosition(xPos, yPos);
    }

    /**
     * Set the ball's movement direction.
     * @param radians the ball's movement direction in radians. 0=right, PI=left.
     */
    public void setDirection(float radians) {
        body.getLoc().setDirection(radians);
    }

    /**
     * Set the ball's speed.
     * @param speed the ball's speed to set.
     */
    public void setSpeed(float speed) {
        body.getLoc().setSpeed(speed);
    }

    /**
     * Set the ball's max speed.
     * @param maxSpeed the ball's max speed.
     */
    public void setMaxSpeed(float maxSpeed) {
        body.getLoc().setMaxSpeed(maxSpeed);
    }

    /**
     * Calculate the shortest distance towards another body.
     * @param otherBody the other body to check distance too.
     * @return the shortest distance towards another body.
     */
    public float distance(Body otherBody) {
        return body.distance(otherBody);
    }

    /**
     * Calculate the distance towards this body and a given point.
     * @param xPos the point's X position.
     * @param yPos the point's Y position.
     * @return the shortest distance towards the body and the given point.
     */
    public float distance(float xPos, float yPos) {
        return body.distance(xPos, yPos);
    }

    /**
     * Move the ball a given distance based on it's speed and direction.
     * No collision handling in this method.
     * @param deltaTime the deltaTime on how far to move the ball. Multiplied into the speed.
     */
    public void move(float deltaTime) {
        body.getLoc().move(deltaTime);
    }

    /**
     * Get the CircleBody instance behind the Ball class.
     * @return the CircleBody instance behind this ball.
     */
    public CircleBody getBody() {
        return body;
    }

}
