package Tests;

import Objects.Pad;
import Objects.Player;
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
    double padXPos=100;
    double padYPos=100;
    float padSpd=100;
    @BeforeEach
    public void beforeEach() {

         pad = new Pad(padL,padW,padXPos,padYPos,padSpd);
    }

    @Test
    public void getters(){
        Assertions.assertEquals(pad.getLength(),padL);
        Assertions.assertEquals(pad.getWidth(),padW);
        Assertions.assertEquals(pad.getPadXPos(),padXPos);
        Assertions.assertEquals(pad.getPadYPos(),padYPos);
        Assertions.assertEquals(pad.getPadSpeed(),padSpd);
    }
    @Test
    public void PadMoveTest() {
        //check move with parameters
        pad.PadMove(0,0); //insert new cordinates uses setPadXPos and setPadYPos
        Assertions.assertEquals(pad.getPadXPos() , 0);
        Assertions.assertEquals(pad.getPadYPos() , 0);

        pad.setSpeed(10); //set new speed and check that speed
        Assertions.assertEquals(pad.getPadSpeed(),10);

        // Get the next X and Y coordinate
        double newX = pad.getPadXPos() + pad.getPadSpeed(); //Verifying the next position
        double newY = pad.getPadYPos() + pad.getPadSpeed();

        pad.PadMove(); //move the pad with regards to the speed variable
        //Check that the pad has moved
        Assertions.assertEquals(pad.getPadXPos() , newX);
        Assertions.assertEquals(pad.getPadYPos() , newY);
    }

}