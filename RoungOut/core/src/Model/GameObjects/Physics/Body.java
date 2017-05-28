package Model.GameObjects.Physics;

/**
 * Interface allowing the body instances to interact with each other.
 *
 * @author Ken Bäcklund
 *
 */
public interface Body {

    // Getters ////////////////////////////////////////////////////////////////

    /**
     * Get X position of the board.
     * @return the X position of the board.
     */
    float getX();

    /**
     * Get the Y position of the board
     */
    float getY();

    /**
     * Get the width of the board.
     * @return the board width
     */
    float getWidth();

    /**
     * Get the height of the board
     * @return the board height
     */
    float getHeight();



    // Setters ////////////////////////////////////////////////////////////////

    /**
     * Set the board X position.
     * @param xPos the board X position to set.
     */
    void setX(float xPos);

    /**
     * Set the board Y position.
     * @param yPos the board Y position to set.
     */
    void setY(float yPos);

    /**
     * Set the boards position.
     * @param xPos the X position to set.
     * @param yPos the Y position to set.
     */
    void setPosition(float xPos, float yPos);

    /**
     * Set the width of the board.
     * @param width the board width.
     */
    void setWidth(float width);

    /**
     * Set the height of the board.
     * @param height the board height.
     */
    void setHeight(float height);



    // Misc ///////////////////////////////////////////////////////////////////

    /**
     * Get the outer distance to another Body object between nearest edges.
     * @param body the body to get the distance to.
     * @return the outer distance between nearest edges.
     */
    float distance(Body body);

    /**
     * Get the distance from a point towards the nearest edge of the body.
     * @param xPos the X position of the point.
     * @param yPos the Y position of the point.
     * @return the distance from the point to nearest edge of the body.
     */
    float distance(float xPos, float yPos);

    /**
     * Get access directly to the Location class used by the body.
     * @return the Location class used by the body.
     */
    Location getLoc();
}
