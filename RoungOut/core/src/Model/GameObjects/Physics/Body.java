package Model.GameObjects.Physics;

/**
 * Interface allowing the body instances to interact with each other.
 * @author Ken Bäcklund
 *
 */
public interface Body {
    /**
     * Getters for
     * X-Position
     * Y-Position
     * Delta-X
     * Delta-Y
     * Direction
     * Speed
     * Width
     * Height
     */

    float getX();
    float getY();
    float getWidth();
    float getHeight();

    /**
     * Setters for:
     * X-Position
     * Y-Position
     * (X-Position, Y-Position)
     * Direction
     * Speed
     * MaxSpeed
     * Width
     * Height
     */
    void setX(float xPos);
    void setY(float yPos);
    void setPosition(float xPos, float yPos);
    void setWidth(float width);
    void setHeight(float height);

    /**
     * Calculates distance from this body to another body
     * @param body The pother body
     * @return Returns the distance
     */
    float distance(Body body);
    float distance(float xPos, float yPos);

    /**
     *  Getter for Location
     * @return Location
     */
    Location getLoc();
}
