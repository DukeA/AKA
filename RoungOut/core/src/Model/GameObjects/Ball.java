package Model.GameObjects;

import Model.GameObjects.Physics.Body;
import Model.GameObjects.Physics.CircleBody;

/**
 * @author Ken Bäcklund
 */
public class Ball implements IModel {

    private CircleBody body;

    public Ball(float xPos, float yPos, float radius, float angle, float speed) {
        body = new CircleBody(xPos, yPos, radius, angle, speed);
    }

    public Ball(float xPos, float yPos, float radius) {
        body = new CircleBody(xPos, yPos, radius, 0f, 0f);
    }

    public float getX() {
        return body.getX();
    }

    public float getY() {
        return body.getY();
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

    public double distance(Body otherBody) {
        return body.distance(otherBody);
    }

    public double distance(float xPos, float yPos) {
        return body.distance(xPos, yPos);
    }

    public void move() {
        body.move();
    }

    public Body getBody(){return body;}

    public void keyWasPressed(int key) {
        // Nothing to do here
    }
}
