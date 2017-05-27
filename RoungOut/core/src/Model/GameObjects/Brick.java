package Model.GameObjects;

import Model.GameObjects.Physics.*;

import java.awt.*;

/**
 * @author Ken Bäcklund
 */
public class Brick implements IModel, IBrick {

    public enum BrickType {
        NORMAL, DESTROYED, SPEED_UP_BALL, SLOW_DOWN_BALL
    }

    protected RectangleBody body;
    protected BrickType brickvalue;


    public Brick(float xPos, float yPos, float width, float height, float angle, float speed) {
        body = new RectangleBody(xPos, yPos, width, height, angle, speed);
        brickvalue = BrickType.NORMAL;
    }

    public Brick(float xPos, float yPos, float width, float height) {
        body = new RectangleBody(xPos, yPos, width, height, 0f, 0f);
        brickvalue = BrickType.NORMAL;
    }
    public float getWidth(){
        return body.getWidth();
    }
    public float getHeight() {
        return body.getHeight();
    }

    public BrickType getBrickType() {
        return brickvalue;
    }

    public void markDestroyed() {
        brickvalue = BrickType.DESTROYED;
    }

    public boolean isDestroyed() {
        return brickvalue == BrickType.DESTROYED;
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

    public void setX(float xPos) {
        body.setX(xPos);
    }

    public void setY(float yPos) {
        body.setY(yPos);
    }

    public void setPosition(float xPos, float yPos) {
        body.setPosition(xPos, yPos);
    }

    public double distance(Body otherBody) {
        return body.distance(otherBody);
    }

    public double distance(float xPos, float yPos) {
        return body.distance(xPos, yPos);
    }
}
