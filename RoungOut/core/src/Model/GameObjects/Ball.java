package Model.GameObjects;

/**
 * @author Ken Bäcklund
 */
public class Ball implements IModel {

    private CircleBody body;

    public Ball(double xPos, double yPos, double radius) {
        body = new CircleBody(xPos, yPos, radius, 0f, 0f);
    }

    public double getX() {
        return body.getX();
    }

    public double getY() {
        return body.getY();
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

    public void move() {
        body.move();
    }
}
