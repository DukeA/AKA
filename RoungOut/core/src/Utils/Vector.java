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


    public Vector(float xPos, float yPos, float xPos2, float yPos2) {
        this.xPos = xPos - xPos2;
        this.yPos = yPos - yPos2;

    }

    public Vector(float xPos, float yPos) {
        this.xPos = xPos;
        this.yPos = yPos;
    }

    public Vector(Vector vector) {
        this.xPos = vector.xPos;
        this.yPos = vector.yPos;
    }
    /**
     * The following two classes are to calculate the crossProudct
    */
    public float crossProudct(Vector vector1, Vector vector2) {
        return (vector1.xPos * vector2.yPos) - (vector1.yPos * vector2.xPos);
    }

    public Vector crossProudct(Vector vector) {
        return new Vector(vector.yPos, -vector.xPos);
    }

    /**
     * The Normalization of a Vector
     */

    public Vector Nomalize(Vector vector) {
        double length = Math.sqrt(Math.pow(vector.xPos, 2) + Math.pow(vector.xPos, 2));
        return new Vector((float) (vector.xPos / length), (float) (vector.yPos / length));
    }

}
