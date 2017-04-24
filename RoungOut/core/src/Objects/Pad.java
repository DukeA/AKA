package Objects;


/**
 * Created by Alex on 2017-04-01.
 */
public class Pad {

    //Variables for objects
    private float length;
    private float height;
    private double padXPos;
    private double padYPos;
    private float padSpeed;

    //Getters
    public float getLength() {
        return length;
    }
    public float getHeight() {
        return height;
    }
    public double getPadXPos() {
        return padXPos;
    }
    public double getPadYPos() {
        return padYPos;
    }
    public float getPadSpeed() {
        return padSpeed;
    }

    //Setters
    public void setPadXPos(double padXPos) {
        this.padXPos = padXPos;
    }
    public void setPadYPos(double padYPos) {
        this.padYPos = padYPos;
    }
    public void setSpeed(float speed) {
        this.padSpeed = speed;
    }

    public Pad(float length, float height, double padXPos, double padYPos,float padSpeed) {
        this.length = length;
        this.height = height;
        this.padXPos = padXPos;
        this.padYPos = padYPos;
        this.padSpeed = padSpeed;
    }
    public void PadMove(double xPos, double yPos) {
        this.padXPos = xPos;
        this.padYPos = yPos;
    }


}
