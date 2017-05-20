package Model.GameObjects;


import Model.GameObjects.Physics.Body;
import Model.GameObjects.Physics.RectangleBody;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector;


/**
 * Created by Alex on 2017-04-01.
 */
public class Pad {

    private RectangleBody body;
    private float originX;
    private float originY;
    private float xRotated;
    private float yRotated;

    private float scalex;
    private float scaley;


    //Completly revamped, barely needed since a body is a pad

    //Getters
    public float getScalex() {
        return scalex;
    }
    public float getScaley() {
        return  scaley;
    }
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

    public void setScalex(float scalex) {
        this.scalex = scalex;
    }
    public void setScaley(float scaley) {
        this.scaley = scaley;
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
        this.body.setAngle(0);
    }

    //Method: Moves by incrementing the x and y pos with it's speed
    public void padMoveRight() {

        float x = body.getX();
        float y = body.getY();


        //float angle = (float)Math.acos();

        double rot = -1.0/180.0*Math.PI;
        double cs = Math.cos(rot);
        double sn = Math.sin(rot);

        double translated_x = x - originX;
        double translated_y = y - originY;

        double result_x = translated_x * cs - translated_y * sn;
        double result_y = translated_x * sn + translated_y * cs;

        result_x += originX;
        result_y += originY;



        body.setX((float)result_x);
        //padXPos = padXPos + padSpeed;
        body.setY((float)result_y);


    }

    public void padMoveLeft() {
        //float x = body.getX()-originX;
        //float y = body.getY()-originY;

        float x = body.getX();
        float y = body.getY();

        //float angle = (float)Math.acos();

        double rot = 1.0/180.0*Math.PI;
        double cs = Math.cos(rot);
        double sn = Math.sin(rot);

        double translated_x = x - originX;
        double translated_y = y - originY;

        double result_x = translated_x * cs - translated_y * sn;
        double result_y = translated_x * sn + translated_y * cs;

        result_x += originX;
        result_y += originY;


        body.setX((float)result_x);
        //padXPos = padXPos + padSpeed;
        body.setY((float)result_y);
        //padYPos = padYPos + padSpeed;
    }

    //Overloaded Method: This simply sets the X and Y pos to the given values
    public void padMove(float xPos, float yPos) {
        setPadXPos(xPos);
        setPadYPos(yPos);
    }


}
