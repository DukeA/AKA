package Model.GameObjects.Physics;

import Model.GameObjects.IModel;

/**
 * Created by kendu on 2017-05-01.
 */
public class Brick implements IModel, Body {

    private RectangleBody body;

    public Brick(double xPos, double yPos, double width, double height, double angle, double speed) {
        body = new RectangleBody(xPos, yPos, width, height, angle, speed);
    }

    public Brick(double xPos, double yPos, double width, double height) {
        body = new RectangleBody(xPos, yPos, width, height, 0f, 0f);
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

    public void setX(double xPos) {
        body.setX(xPos);
    }

    public void setY(double yPos) {
        body.setY(yPos);
    }

    public void setPosition(double xPos, double yPos) {
        body.setPosition(xPos, yPos);
    }

    public void setAngle(double radians) {
        body.setAngle(radians);
    }

    public void setSpeed(double speed) {
        body.setSpeed(speed);
    }

    public void setMaxSpeed(double maxSpeed) {
        body.setMaxSpeed(maxSpeed);
    }

    public double distance(Body otherBody) {
        return body.distance(otherBody);
    }

    public double distance(double xPos, double yPos) {
        return body.distance(xPos, yPos);
    }

    public void move() {
        body.move();
    }
}
