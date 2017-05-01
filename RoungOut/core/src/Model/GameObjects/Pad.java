package Model.GameObjects;


import Model.GameObjects.Physics.RectangleBody;

/**
 * Created by Alex on 2017-04-01.
 */
public class Pad {

    private RectangleBody body;

    //Completly revamped, barely needed since a body is a pad

    //Getters

    public double getLength() {
        return body.getHeight();
    }
    public double getWidth() {
        return body.getWidth();
    }
    public double getPadXPos() {
        return body.getX();
    }
    public double getPadYPos() {
        return body.getX();
    }
    public double getPadSpeed() {
        return body.getSpeed();
    }



    //Setters
    public void setPadXPos(double padXPos) {
        body.setX(padXPos);
    }
    public void setPadYPos(double padYPos) {
        body.setY(padYPos);
    }
    public void setSpeed(float speed) {
        body.setSpeed(speed);
    }

    //Constructor
    public Pad(float length, float width, double padXPos, double padYPos,float padSpeed) {
        this.body =new RectangleBody(padXPos,padYPos,width,length);
        this.setSpeed(padSpeed);
    }

    //Method: Moves by incrementing the x and y pos with it's speed
    public void padMove(){
        body.setX(body.getX() + body.getSpeed());
        //padXPos = padXPos + padSpeed;
        body.setY(body.getY()+body.getSpeed());
        //padYPos = padYPos + padSpeed;
    }

    //Overloaded Method: This simply sets the X and Y pos to the given values
    public void padMove(double xPos, double yPos) {
        setPadXPos(xPos);
        setPadYPos(yPos);
    }


}
