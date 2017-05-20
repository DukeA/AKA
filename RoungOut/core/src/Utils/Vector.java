package Utils;

/**
 * Created by DukeA on 2017-05-20.
 */

/**
 * The Class is  a help class for the Pad movement
 */
public class Vector {

    private float xPos;
    private float yPos;
    private float zPos;


    public Vector(float xPos, float yPos, float xPos2, float yPos2) {
        this.xPos = xPos - xPos2;
        this.yPos = yPos - yPos2;
        this.zPos = 0;

    }

    public Vector(float xPos, float yPos, float zPos) {
        this.xPos = xPos;
        this.yPos = yPos;
        this.zPos = zPos;
    }

    public Vector(Vector vector) {
        this.xPos = vector.xPos;
        this.yPos = vector.yPos;
        this.zPos = vector.zPos;
    }

    /**
     * The following two classes are to calculate the crossProudct
     */
    public Vector crossProudct() {
        Vector vectorZ = new Vector(0, 0, 1);
        return new Vector(this.yPos * vectorZ.zPos - this.zPos * vectorZ.yPos,
                this.zPos * vectorZ.xPos - this.xPos * vectorZ.zPos,
                this.xPos * vectorZ.yPos - this.yPos * vectorZ.xPos);
    }
    /**
     * The Normalization of a Vector
     */

    public Vector normalization() {
        double length = Math.sqrt(Math.pow(this.xPos, 2) + Math.pow(this.yPos, 2));
        return new Vector((float) (this.xPos / length), (float) (this.yPos / length)
                , (float) (this.zPos / length));
    }

    public Vector vectorLength(float length) {
        return new Vector(this.xPos * length / 2,
                this.yPos * length / 2,
                this.zPos * length / 2);
    }

    public Vector vectorWidth(float width) {
        return new Vector(this.xPos * width / 2,
                this.yPos * width / 2,
                this.zPos * width / 2);
    }

    public float getxPos() {
        return this.xPos;
    }

    public float getyPos() {
        return this.yPos;
    }

    public float getzPos() {
        return this.zPos;

    }
}
