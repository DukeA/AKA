package Model.GameObjects.Physics;

/**
 * Create a body with a circular shape.
 *
 * @author Ken Bäcklund
 * Modified by Alex 07-05-17
 */
public class CircleBody implements Body {

    // Private variables //////////////////////////////////////////////////////

    /**
     * The Location class that handles most logic for the body.
     */
    private Location location;

    /**
     * The radius of the circle.
     */
    private float radius;


    // Constructors ///////////////////////////////////////////////////////////

    /**
     * Create a circular body with a given position, size, direction and speed.
     * @param xPos the X position of the circle body.
     * @param yPos the Y position of the circle body.
     * @param radius the radius of the circle body.
     * @param angle the direction in radians the body moves towards.
     * @param speed the speed the circular body moves at.
     */
    public CircleBody(float xPos,   float yPos,
                      float radius, float angle, float speed) {
        location = new Location(xPos, yPos, angle, speed);
        this.radius=radius;
    }

    /**
     * Create a circular body with a given position and radius.
     * @param xPos the X position of the circle body.
     * @param yPos the Y position of the circle body.
     * @param radius the radius of the circle body.
     */
    public CircleBody(float xPos, float yPos, float radius) {
        location = new Location(xPos, yPos, 0f, 0f);
        this.radius=radius;
    }

    /**
     * Create a circular body with a give´n radius, at fixed position 0,0.
     * @param radius the radius of the circle body.
     */
    public CircleBody(float radius) {
        location = new Location(0,0,0,0);
        this.radius = radius;
    }

    /**
     * Create a circular body by cloning another CircleBody.
     * @param circleBody the CircleBody instance to clone.
     */
    public CircleBody(CircleBody circleBody) {
        this.location = new Location(circleBody.getLoc());
        this.radius = circleBody.getRadius();
    }



    // Getters ////////////////////////////////////////////////////////////////

    /**
     * Get the X position of the circle body.
     * @return the X position of the circle body.
     */
    public float getX() {
        return location.getX();
    }

    /**
     * Get the Y position of the circle body.
     * @return the Y position of the circle body.
     */
    public float getY() {
        return location.getY();
    }

    /**
     * Get the horizontal speed of the body. Positive=right direction.
     * @return the horizontal speed of the circle body.
     */
    public float getDeltaX() {
        return location.getDeltaX();
    }

    /**
     * Get the vertical speed of the body. Positive=upwards direction.
     * @return the vertical speed of the circle body.
     */
    public float getDeltaY() {
        return location.getDeltaY();
    }

    /**
     * Get the width of the circle body (ie. 2*radius).
     * @return the width of the circle body, 2*radius.
     */
    public float getWidth() {
        return radius*2;
    }

    /**
     * Get the height of the circle body (ie. 2*radius).
     * @return the height of the circle body, 2*radius.
     */
    public float getHeight() {
        return radius*2;
    }

    /**
     * Get access to the Location instance used by the circle body.
     * @return the Location instance used by the body.
     */
    public Location getLoc() {return location;}

    /**
     * Get the radius of the circle body.
     * @return the radius of the circle body.
     */
    public float getRadius(){return radius;}



    // Setters ////////////////////////////////////////////////////////////////

    /**
     * Set the X position of the circle body.
     * @param xPos the X position to set.
     */
    public void setX(float xPos) {
        location.setX(xPos);
    }

    /**
     * Set the Y position of the circle body.
     * @param yPos the X position to set.
     */
    public void setY(float yPos) {
        location.setY(yPos);
    }

    /**
     * Set the position of the circle body.
     * @param xPos the X position to set.
     * @param yPos the Y position to set.
     */
    public void setPosition(float xPos, float yPos) {
        location.setPosition(xPos, yPos);
    }

    /**
     * Set the width of the circle body (ie. radius/2).
     * @param width the width of the circle body (ie. radius/2).
     */
    public void setWidth(float width) {
        this.radius=Math.abs(width/2);
    }

    /**
     * Set the height of the circle body (ie. radius/2).
     * @param height the width of the circle body (ie. radius/2).
     */
    public void setHeight(float height) {
        this.radius=Math.abs(height/2);
    }



    // Other methods //////////////////////////////////////////////////////////

    /**
     * Get the distance from a given point toward nearest edge of this body.
     * @param xPos the X position of the point.
     * @param yPos the Y position of the point.
     * @return the distance from the point towards closest edge of the body.
     */
    public float distance(float xPos, float yPos) {
        return Math.max(0, location.distance(xPos, yPos) - getRadius());
    }

    /**
     * Get the outer distance from another body.
     * @param body the body to get the distance to.
     * @return the distance between closest edges of both bodies.
     */
    public float distance(Body body) {
        float dCenterPoints = location.distance(body.getX(), body.getY());
        float dToOther = distance(body.getX(), body.getY());
        float dFromOther = body.distance(this.getX(), this.getY());
        return Math.max(0, (dToOther + dFromOther)- dCenterPoints);
    }

}
