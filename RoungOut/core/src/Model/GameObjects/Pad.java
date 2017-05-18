package Model.GameObjects;


import Model.GameObjects.Physics.Body;
import Model.GameObjects.Physics.RectangleBody;

/**
 * Created by Alex on 2017-04-01.
 */
public class Pad {

    private RectangleBody body;
    private Float originX;
    private Float originY;
    private Float radius;


    //Completly revamped, barely needed since a body is a pad

    //Getters
    public float getOriginX(){
        return originX;
    }
    public float getOriginY() {
        return  originY;
    }
    public float getLength() {
        return body.getHeight();
    }

    public float getWidth() {
        return body.getWidth();
    }

    public float getPadXPos() {
        return body.getX();
    }

    public float getPadYPos() {
        return body.getY();
    }

    public float getPadSpeed() {
        return body.getSpeed();
    }

    public float getAngle(){return body.getAngle();}

    public Body getBody() {
        return body;
    }
    public void setOriginX(float padXPos)  {
        this.originX =padXPos;
    }
    public void setOriginY(float padYPos) {
        this.originY = padYPos;
    }

    //Setters, mainly used in testing
    public void setPadXPos(float padXPos) {
        body.setX(padXPos);
    }

    public void setPadYPos(float padYPos) {
        body.setY(padYPos);
    }

    public void setSpeed(float speed) {
        body.setSpeed(speed);
    }

    //Constructor
    public Pad(float length, float width, float originX, float originY, float padXPos, float padYPos, float padSpeed) {
        this.body = new RectangleBody(padXPos, padYPos, width, length);
        this.body.setSpeed(padSpeed);
        this.originY = originY;
        this.originX =originX;
        this.body.setAngle(270);
    }

    //Method: Moves by incrementing the x and y pos with it's speed
    public void padMoveRight() {
        this.radius =getPadXPos()/getPadYPos();
        originX = body.getX() + body.getSpeed();
        body.setX(originX + (float)Math.cos(30f) *radius);
        //padXPos = padXPos + padSpeed;
        originY = body.getX() + body.getSpeed();
        body.setY(originY + (float)Math.sin(30f) *radius);
        //padYPos = padYPos + padSpeed;
    }

    public void padMoveLeft() {
        this.radius =getPadXPos()/getPadYPos();
        originX = body.getX() + (body.getSpeed() * -1);
        body.setX(originX - (float)Math.cos(30f) * radius);
        //padXPos = padXPos + padSpeed;
        originY = body.getY() + (body.getSpeed() * -1);
        body.setY(originY - (float)Math.sin(30f) * radius);
        //padYPos = padYPos + padSpeed;
    }

    //Overloaded Method: This simply sets the X and Y pos to the given values
    public void padMove(float xPos, float yPos) {
        setPadXPos(xPos);
        setPadYPos(yPos);
    }


}
