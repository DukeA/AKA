package Tests;

import Model.GameObjects.Pad;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Created by Alex on 2017-04-24.
 * Bases on MovePadTest created by DukeA on 2017-04-04
 */
public class PadTest {
    Pad pad;
    //Values that is used in these test cases
    float padL=20;
    float padW=10;
    float originX =0;
    float originY =0;
    float padXPos=100;
    float padYPos=100;
    float padSpd=100;
    @BeforeEach
    public void beforeEach() {

         pad = new Pad(padL,padW,originX,originY,padXPos,padYPos,padSpd);
    }

    @Test
    public void getters(){
        Assertions.assertEquals(pad.getLength(),padL);
        Assertions.assertEquals(pad.getWidth(),padW);
        Assertions.assertEquals(pad.getPadXPos(),padXPos);
        Assertions.assertEquals(pad.getPadYPos(),padYPos);
        Assertions.assertEquals(pad.getPadSpeed(),padSpd);

        //Also, try the same thing but go via getBody
        Assertions.assertEquals(pad.getBody().getHeight(),padL);
        Assertions.assertEquals(pad.getBody().getWidth(),padW);
        Assertions.assertEquals(pad.getBody().getX(),padXPos);
        Assertions.assertEquals(pad.getBody().getY(),padYPos);
        Assertions.assertEquals(pad.getSpeed(),padSpd);
    }
    @Test
    public void padMoveTest() {
        //check move with parameters
        pad.padMove(0,0); //insert new cordinates uses setPadXPos and setPadYPos
        Assertions.assertEquals(pad.getPadXPos() , 0);
        Assertions.assertEquals(pad.getPadYPos() , 0);

        pad.setSpeed(10); //set new speed and check that speed
        Assertions.assertEquals(pad.getPadSpeed(),10);

        //Get the current positions, will soon be the old pos
        float oldX = pad.getPadXPos();
        float oldY = pad.getPadYPos();

        double rot = 1.0/180.0*Math.PI;
        double cs = Math.cos(rot);
        double sn = Math.sin(rot);

        double translated_x = oldX - originX;
        double translated_y = oldY - originY;

        double result_x = translated_x * cs - translated_y * sn;
        double result_y = translated_x * sn + translated_y * cs;

        result_x += originX;
        result_y += originY;



        // Get the next X and Y coordinate
        float newX = (float) result_x; //Verifying the next position
        float newY = (float) result_y;

        pad.padMoveRight(); //move the pad with regards to the speed variable
        //Check that the pad has moved
        Assertions.assertEquals(pad.getPadXPos() , newX);
        Assertions.assertEquals(pad.getPadYPos() , newY);

        


        pad.padMoveLeft(); //move the otherway
        Assertions.assertEquals(pad.getPadXPos() , oldX);
        Assertions.assertEquals(pad.getPadYPos() , oldY);
    }

}