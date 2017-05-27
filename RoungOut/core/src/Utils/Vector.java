package Utils;

/**
 * @author Adam
 * Created on 2017-05-20.
 */

/**
 * The Class is  a help class for the Pad movement
 */
public class Vector {

    private float xPos;
    private float yPos;
    private float zPos;

    /**
     * Constructor
     * @param xPos X-Position of the first point
     * @param yPos Y-Position of the first point
     * @param xPos2 X-Position of the second point
     * @param yPos2 Y-Position of the second point
     */
    public Vector(float xPos, float yPos, float xPos2, float yPos2) {
        this.xPos = xPos - xPos2;
        this.yPos = yPos - yPos2;
        this.zPos = 0; //2D , zed dimension is not needed

    }

    /**
     * Constructor, the vector is this point and the origin
     * @param xPos X-Position of the point
     * @param yPos Y-Position of the point
     * @param zPos Z-Position of the point
     */
    public Vector(float xPos, float yPos, float zPos) {
        this.xPos = xPos;
        this.yPos = yPos;
        this.zPos = zPos;
    }

    /**
     * Clone Constructor
     */
    public Vector(Vector vector) {
        this.xPos = vector.xPos;
        this.yPos = vector.yPos;
        this.zPos = vector.zPos;
    }

    /**
     * This method calculates the cross product
     * @return The cross product
     */
    public Vector crossProudct() {
        Vector vectorZ = new Vector(0, 0, 1);
        return new Vector(this.yPos * vectorZ.zPos - this.zPos * vectorZ.yPos,
                this.zPos * vectorZ.xPos - this.xPos * vectorZ.zPos,
                this.xPos * vectorZ.yPos - this.yPos * vectorZ.xPos);
    }
    /**
     * This method returns a normalization of a Vector
     * @return The normalization of the vector
     */
    public Vector normalization() {
        double length = Math.sqrt(Math.pow(this.xPos, 2) + Math.pow(this.yPos, 2));
        return new Vector((float) (this.xPos / length), (float) (this.yPos / length)
                , (float) (this.zPos / length));
    }

    /**
     * This method given a length of a rectangular shape, returns the vector from the middle point to the edge
     * (length-wise) of the shape
     * @param length
     * @return The vector from the middle point to the edge
     */
    public Vector vectorLength(float length) {
        return new Vector(this.xPos * length / 2,
                this.yPos * length / 2,
                this.zPos * length / 2);
    }
    /**
     * This method given a width of a rectangular shape, returns the vector from the middle point to the edge
     * (length-wise) of the shape
     * @param width
     * @return The vector from the middle point to the edge
     */
    public Vector vectorWidth(float width) {
        return new Vector(this.xPos * width / 2,
                this.yPos * width / 2,
                this.zPos * width / 2);
    }

    /**
     * Getter, gets the X-Position of this Vector
     * @return X-Position
     */
    public float getxPos() {
        return this.xPos;
    }

    /**
     * Getter, gets the Y-Position of this Vector
     * @return Y-Position
     */
    public float getyPos() {
        return this.yPos;
    }

    /**
     * Getter, gets the Z-Position of this Vector
     * @return Z-Position
     */
    public float getzPos() {return this.zPos;}
}
