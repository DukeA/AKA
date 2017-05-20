package Model.GameObjects;

import Model.GameObjects.Physics.Body;
import Model.GameObjects.Physics.CircleBody;

import java.util.HashSet;
import java.util.Set;


/**
 * @author Ken Bäcklund
 * Modified by Alex 07-05-17
 */
public class Ball implements IBall {

    private CircleBody body;

    public Ball(float xPos, float yPos, float radius, float angle, float speed) {
        body = new CircleBody(xPos, yPos, radius, angle, speed);
    }

    public Ball(float xPos, float yPos, float radius) {
        this(xPos, yPos, radius, 0f, 0f);
    }

    public Ball(Ball ball) {
        this(ball.getX(), ball.getY(), ball.getRadius(), ball.getAngle(), ball.getSpeed());
    }

    public float getX() {
        return body.getX();
    }

    public float getY() {
        return body.getY();
    }

    public float getDeltaX() {
        return body.getDeltaX();
    }

    public float getDeltaY() {
        return body.getDeltaY();
    }

    public float getAngle() {
        return body.getAngle();
    }

    public float getSpeed() {
        return body.getSpeed();
    }

    public float getRadius() {
        return body.getRadius();
    }

    public void setX(float xPos) {
        body.setX(xPos);
    }

    public void setY(float yPos) {
        body.setY(yPos);
    }

    public void setPosition(float xPos, float yPos) {
        body.setPosition(xPos, yPos);
    }

    public void setAngle(float radians) {
        body.setAngle(radians);
    }

    public void setSpeed(float speed) {
        body.setSpeed(speed);
    }

    public void setMaxSpeed(float maxSpeed) {
        body.setMaxSpeed(maxSpeed);
    }

    public float distance(Body otherBody) {
        return body.distance(otherBody);
    }

    public float distance(float xPos, float yPos) {
        return body.distance(xPos, yPos);
    }

    public void move(float deltaTime) {
        body.move(deltaTime);
    }

    public Body getBody(){return body;}

    public CircleBody getball() {
        return this.body;
    }

}
