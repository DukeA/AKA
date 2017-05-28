package Model.GameObjects.Physics;

/**
 * @author Ken Bäcklund
 * Modified by Alex 07-05-17
 */
public class CircleBody implements Body {

    private Location location;
    private float radius;

    // Constructors ///////////////////////////////////////////////////////////

    /**
     * Constructor
     * @param xPos Position on X-Axis.
     * @param yPos Position on Y-Axis
     * @param radius The radius of the circle
     * @param angle The angular direction of the circle (0 degrees = Right, as degrees increment the angle rotates counter-clockwise)
     * @param speed The speed of the circle
     */
    public CircleBody(float xPos, float yPos, float radius, float angle, float speed) {
        location = new Location(xPos, yPos, angle, speed);
        this.radius=radius;
    }

    /**
     * Constructor
     * @param xPos Position on X-Axis
     * @param yPos Position on Y-Axis
     * @param radius The radius of the circle
     */
    public CircleBody(float xPos, float yPos, float radius) {
        location = new Location(xPos, yPos, 0f, 0f);
        this.radius=radius;
    }

    /**
     * Constructor
     * @param radius The radius of the circle
     */
    public CircleBody(float radius) {
        location = new Location(0,0,0,0);
        this.radius = radius;
    }
    /**
     * Clone Constructor
     * @param c c is the circle that is to be cloned
     */
    public CircleBody(CircleBody c) {
        this.location = new Location(c.getLoc());
        this.radius = c.getRadius();
    }
    // Getters ////////////////////////////////////////////////////////////////

    /**
     * Getter, gets X-position
     * @return X-Position
     */
    public float getX() {
        return location.getX();
    }

    /**
     * Getter, gets Y-Pos
     * @return Y-Position
     */
    public float getY() {
        return location.getY();
    }

    /**
     * Getter, gets the X-Pos the circle will have after the next move
     * @return predicted X-Pos
     */
    public float getDeltaX() {
        return location.getDeltaX();
    }

    /**
     * Getter, gets the Y-Pos the circle will have after the next move
     * @return predicted Y-Pos
     */
    public float getDeltaY() {
        return location.getDeltaY();
    }

    /**
     * Getter, gets the width of the circle
     * @return the width of the circle
     */
    public float getWidth() {
        return radius*2;
    }

    /**
     * Getter, gets the height of the circle
     * @return the height of the circle
     */
    public float getHeight() {
        return radius*2;
    }

    /**
     * Getter, gets the location data
     * @return the Location instance
     */
    public Location getLoc() {return location;}



    /**
     * Getter, gets the radius
     * @return returns the radius
     */
    public float getRadius(){return radius;}



    // Setters ////////////////////////////////////////////////////////////////

    /**
     * Setter, sets X-Position
     * @param xPos the X-Position
     */
    public void setX(float xPos) {
        location.setX(xPos);
    }

    /**
     * Setter, sets Y-Position
     * @param yPos the Y-Position
     */
    public void setY(float yPos) {
        location.setY(yPos);
    }

    /**
     * Setter, sets X&Y position
     * @param xPos the X-Position
     * @param yPos the Y-Position
     */
    public void setPosition(float xPos, float yPos) {
        location.setPosition(xPos, yPos);
    }

    /**
     * Setter, sets the width
     * @param width the width
     */
    public void setWidth(float width) {
        this.radius=Math.abs(width/2);
    }

    /**
     * Setter, sets the height
     * @param height the height
     */
    public void setHeight(float height) {
        this.radius=Math.abs(height/2);
    }



    // Other methods //////////////////////////////////////////////////////////

    /**
     * Calculates the other distance to the X&Y Pos (from the circle's point)
     * @param xPos the X-Position
     * @param yPos the Y-Position
     * @return the distance to that point to nearest edge of the body.
     */
    public float distance(float xPos, float yPos) {
        return Math.max(0, location.distance(xPos, yPos) - getRadius());
    }

    /**
     * Calculated the outer distance to a body
     * @param body the Body
     * @return the distance between nearest edges between the bodies.
     */
    public float distance(Body body) {
        float dCenterPoints = location.distance(body.getX(), body.getY());
        float dToOther = distance(body.getX(), body.getY());
        float dFromOther = body.distance(this.getX(), this.getY());
        return Math.max(0, (dToOther + dFromOther)- dCenterPoints);
    }

}
