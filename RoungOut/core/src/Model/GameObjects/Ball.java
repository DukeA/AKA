package Model.GameObjects;

/**
 * @author Ken Bäcklund
 */
public class Ball implements IModel, Body {

    private CircleBody body;

    public Ball(double xPos, double yPos, double radius, double angle, double speed) {
        body = new CircleBody(xPos, yPos, radius, angle, speed);
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

    @Override
    public double distance(Body otherBody) {
        return body.distance(otherBody);
    }

    @Override
    public double distance(double xPos, double yPos) {
        return body.distance(xPos, yPos);
    }

    public void move() {
        body.move();
    }
}
