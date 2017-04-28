package Model.GameObjects;

import java.util.Map;

/**
 * Class for handling Bricks
 * @author Ken Bäcklund
 */
public class Brick {

    // PRIVATE VARIABLES //////////////////////////////////////////////////////
    private double xPos;
    private double yPos;
    private double width;       // width > 0.
    private double height;      // height > 0.

    // CONSTRUCTOR ////////////////////////////////////////////////////////////

    /**
     * Creates a new Brick instance.
     * @param x - the X position for the new brick.
     * @param y - the Y position for the new brick.
     * @param width - positive width for the new brick.
     * @param height - positive height for the new brick.
     */
    public Brick(double x, double y, double width, double height) {
        setPosition(x, y);
        setSize(width, height);
    }

    /**
     * Get brick's X position.
     * @return the brick's X position.
     */
    public double getX() {
        return xPos;
    }

    /**
     * Set brick's X position.
     * @param x - the new X position
     */
    public void setX(double x) {
        this.xPos = x;
    }

    /**
     * Get brick's Y position
     * @return the brick's Y position.
     */
    public double getY() {
        return yPos;
    }

    /**
     * Set brick's Y position.
     * @param y - the new Y position.
     */
    public void setY(double y) {
        this.yPos = y;
    }

    /**
     * Set brick's X and Y position.
     * @param x - the X position.
     * @param y - the Y position.
     */
    public void setPosition(double x, double y) {
        setX(x);
        setY(y);
    }

    /**
     * Get brick's width.
     * @return the brick's width.
     */
    public double getWidth() {
        return width;
    }

    /**
     * Set the brick's width.
     * @param width - positive brick width.
     */
    public void setWidth(double width) {
        if (width <= 0.0f) {
            throw new IllegalArgumentException();   // Width must be a positive number.
        }
        this.width = width;
    }

    /**
     * Get brick's height.
     * @return - the brick's height.
     */
    public double getHeight() {
        return height;
    }

    /**
     * Set brick's height.
     * @param height - positive brick height.
     */
    public void setHeight(double height) {
        if (height <= 0.0f) {
            throw new IllegalArgumentException();   // Height must be a positive number.
        }
        this.height = height;
    }

    /**
     * Set brick's width and height.
     * @param width - positive brick width.
     * @param height - positive brick height.
     */
    public void setSize(double width, double height) {
        setWidth(width);
        setHeight(height);
    }

}
