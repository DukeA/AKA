package Model.GameObjects;

import Model.GameObjects.IModel;
import Model.GameObjects.Physics.*;

/**
 * Created by kendu on 2017-05-01.
 */
public class Brick implements IModel {

    public enum BrickType {
        NORMAL, SPEED_UP_BALL, SLOW_DOWN_BALL
    }

    private RectangleBody body;
    private Enum brickvalue;


    public Brick(float xPos, float yPos, float width, float height, float angle, float speed) {
        body = new RectangleBody(xPos, yPos, width, height, angle, speed);
        brickvalue = BrickType.NORMAL;
    }

    public Brick(float xPos, float yPos, float width, float height) {
        body = new RectangleBody(xPos, yPos, width, height, 0f, 0f);
        brickvalue = BrickType.NORMAL;
    }

    public double getX() {
        return body.getX();
    }

    public double getY() {
        return body.getY();
    }

    public double getAngle() {
        return body.getAngle();
    }

    public double getSpeed() {
        return body.getSpeed();
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

    // DO-Nothing
    @Override
    public void moveLeft() {}
    @Override
    public void moveRight() {}
}
