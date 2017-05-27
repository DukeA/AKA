package Model.GameObjects.Physics;

/**
 * Interface allowing the body instances to interact with each other.
 * @author Ken Bäcklund
 *
 */
public interface Body {

    float getX();
    float getY();
    float getWidth();
    float getHeight();

    void setX(float xPos);
    void setY(float yPos);
    void setPosition(float xPos, float yPos);
    void setWidth(float width);
    void setHeight(float height);

    float distance(Body body);
    float distance(float xPos, float yPos);

    Location getLoc();
}
