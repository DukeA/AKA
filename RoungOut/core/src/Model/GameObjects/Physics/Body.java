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

    /**
     * Get the X position of the body.
     * @return the X position of the body.
     */
    float getX();

    /**
     * Get the Y position of the body.
     * @return the Y position of the body.
     */
    float getY();

    /**
     * Get the width of the body.
     * @return the width of the body.
     */
    float getWidth();

    /**
     * Get the height of the body.
     * @return the height of the body.
     */
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

    /**
     * Set the X position of the body.
     * @param xPos the X position of the body.
     */
    void setX(float xPos);

    /**
     * Set the Y position of the body.
     * @param yPos the Y position of the body.
     */
    void setY(float yPos);

    /**
     * Set the position of the body.
     * @param xPos the X position of the body.
     * @param yPos the Y position of the body.
     */
    void setPosition(float xPos, float yPos);

    /**
     * Set the width of the body.
     * @param width the width of the body.
     */
    void setWidth(float width);

    /**
     * Set the height of the body.
     * @param height the height of the body.
     */
    void setHeight(float height);

    /**
     * Calculates the outer distance from this body to another body
     * @param body the other body
     * @return the distance between nearest edge of the two bodies.
     */
    float distance(Body body);

    /**
     * Calculates the outer distance from this body to a given point.
     * @param xPos the X position to check from.
     * @param yPos the Y position to check from.
     * @return the distance between nearest edge and the point.
     */
    float distance(float xPos, float yPos);

    /**
     *  Getter for Location
     * @return Location instance.
     */
    Location getLoc();
}
