package Model.GameObjects;

/**
 * Give a model ability to move across the board.
 * @author Ken Bäcklund
 */
public interface IMovable {
    float getSpeed();
    float getDirection();
    float getDeltaX();
    float getDeltaY();
    void setSpeed(float speed);
    void setMaxSpeed(float maxSpeed);
    void setDirection(float radians);
    void move(float deltaTime);
}
