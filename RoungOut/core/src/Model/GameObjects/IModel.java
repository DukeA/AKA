package Model.GameObjects;

/**
 * @author Ken Bäcklund
 */
public interface IModel {

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
    void move();
    void keyWasPressed(int key);

}
