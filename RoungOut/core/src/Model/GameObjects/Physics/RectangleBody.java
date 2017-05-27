package Model.GameObjects.Physics;

/**
 * @author Ken Bäcklund
 * Modified by Alex 2017-05-07
 */
public class RectangleBody implements Body {

    protected Location location;
    protected float width;
    protected float height;
    //private RectangleShape shape;

    // Constructors ///////////////////////////////////////////////////////////
    public RectangleBody(float xPos, float yPos, float width, float height, float angle, float speed) {
        location = new Location(xPos, yPos, angle, speed);
        this.width=width;
        this.height = height;

        // shape = new RectangleShape(WIDTH, HEIGHT);
    }

    public RectangleBody(float xPos, float yPos, float width, float height) {
        this(xPos, yPos, width, height, 0, 0);
    }

    public RectangleBody(float width, float height) {
        this(0, 0, width, height, 0, 0);
    }

    public RectangleBody(RectangleBody r) {
        this.location = new Location(r.getLoc());
        this.width=r.getWidth();
        this.height=r.getHeight();
    }

    // Private helper methods /////////////////////////////////////////////////

    private float lineDistance(float lineCenterPoint, float otherPoint, float lineSize) {
        // Get the distance from the center of a line segment towards another point.
        return Math.max(0, Math.abs(lineCenterPoint - otherPoint) - lineSize/2f);
    }

    // Getters ////////////////////////////////////////////////////////////////
    @Override
    public float getX() {
        return location.getX();
    }

    @Override
    public float getY() {
        return location.getY();
    }

    @Override
    public float getWidth() {
        return this.width;
    }

    @Override
    public float getHeight() {
        return this.height;
    }

    // Setters ////////////////////////////////////////////////////////////////
    @Override
    public void setX(float xPos) {
        location.setX(xPos);
    }

    @Override
    public void setY(float yPos) {
        location.setY(yPos);
    }

    @Override
    public void setWidth(float width) {
        this.width = width;
    }

    @Override
    public void setHeight(float height) {
        this.height = height;
    }

    @Override
    public void setPosition(float xPos, float yPos) {
        location.setPosition(xPos, yPos);
    }

    @Override
    public Location getLoc() {
        return location;
    }

    // Other methods //////////////////////////////////////////////////////////
    public float distance(Body body) {
        float dCenterPoints = location.distance(body.getX(), body.getY());
        float dToOther = distance(body.getX(), body.getY());
        float dFromOther = body.distance(getX(), getY());
        return Math.max(0, (dToOther + dFromOther)- dCenterPoints);
    }

    public float distance(float xPos, float yPos) {
        float dx = lineDistance(location.getX(), xPos, getWidth());
        float dy = lineDistance(location.getY(), yPos, getHeight());
        return (float) Math.sqrt(dx*dx+dy*dy);
    }

}
