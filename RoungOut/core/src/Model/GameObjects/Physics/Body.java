package Model.GameObjects.Physics;

/**
 * @author Ken Bäcklund
 */
public interface Body {

    double getX();
    double getY();
    double getAngle();
    double getSpeed();

    void setX(double xPos);
    void setY(double yPos);
    void setPosition(double xPos, double yPos);
    void setAngle(double radians);
    void setSpeed(double speed);
    void setMaxSpeed(double maxSpeed);

    double distance(Body body);
    double distance(double xPos, double yPos);

    void move();
    void move(double distance);

}
