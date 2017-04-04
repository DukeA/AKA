package Objects;


/**
 * Created by Alex on 2017-04-01.
 */
public class Pad {

    //Variables for objects
    private float length;
    private float height;
    private float padXPos;
    private float padYPos;
    private float padSpeed =10;

    //Getters
    public float getLength() {
        return length;
    }
    public float getHeight() {
        return height;
    }
    public float getPadXPos() {
        return padXPos;
    }
    public float getPadYPos() {
        return padYPos;
    }
    public float getPadSpeed() {
        return padSpeed;
    }
    //Setters
    public void setPadXPos(float padXPos) {
        this.padXPos = padXPos;
    }
    public void setPadYPos(float padYPos) {
        this.padYPos = padYPos;
    }

    public Pad(float length, float height, float padXPos, float padYPos) {
        this.length = length;
        this.height = height;
        this.padXPos = padXPos;
        this.padYPos = padYPos;
    }

}
