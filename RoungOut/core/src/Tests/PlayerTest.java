package Tests;

import Model.GameObjects.Pad;
import Model.GameObjects.Player;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

/**
 * Created by Alex on 2017-04-24.
 *
 * This is more of a player test than it is a pad test actually
 */
public class PlayerTest {

    Player player;
    //Values used for  these tests
    float PadLength = 12;
    float PadWidth = 30;
    float PadXPos = 100;
    float PadYPos = 100;
    float PadSpeed = 30;

    @BeforeEach
    void setUp(){ player = new Player(PadLength,PadWidth, PadXPos,PadYPos, PadSpeed);}

   @Test
    public void playerMoveTest() {
        //Get the current X and Y pos
       float oldX = player.getPad().getPadXPos();
       float oldY = player.getPad().getPadYPos();


        // Get the next X and Y coordinates
        float nextX = player.getPad().getPadXPos() + player.getPad().getPadSpeed();
        float nextY = player.getPad().getPadYPos() + player.getPad().getPadSpeed();

        player.moveRight();

        //Check that the pad has moved to the desired positions
        //Similar test is in the test for Pad
        Assertions.assertEquals(player.getPad().getPadXPos() , nextX);
        Assertions.assertEquals(player.getPad().getPadYPos() , nextY);

       player.moveLeft();
       //Move back to start pos
       Assertions.assertEquals(player.getPad().getPadXPos() , oldX);
       Assertions.assertEquals(player.getPad().getPadYPos() , oldY);
    }

    @Test
    public void defaultConstructor(){
        Player player2 = new Player();
        //Verifying default by checking its position, position 0,0 is considered default
        Assertions.assertEquals(player2.getPad().getPadXPos(),0);
        Assertions.assertEquals(player2.getPad().getPadYPos(),0);
    }
    @Test
    public void setCordinates(){
        float x =100;
        float y =100;
        player.setPadCoordinates(x,y);

        Assertions.assertEquals(player.getPad().getPadXPos(),x);
        Assertions.assertEquals(player.getPad().getPadYPos(),y);
    }
    @Test
    public void getCordinates(){
        ArrayList<Float> cords = new ArrayList<Float>();
        cords.add(0,PadXPos);
        cords.add(1,PadYPos);

        Assertions.assertEquals(player.getPadCoordinates(),cords);
    }

    @Test
    public void points(){
        Assertions.assertEquals(player.getPoints(),0); //0 is default
        player.setPoints(10);
        Assertions.assertEquals(player.getPoints(),10); //10 is the value we set it to

        player.offsetPoints();
        Assertions.assertEquals(player.getPoints(),11); //10 + 1

        player.offsetPoints(10);
        Assertions.assertEquals(player.getPoints(),21); //10 + 1 + 10
    }
    @Test void setPad(){
        Pad awesomePad = new Pad(100,10,42,42,100);
        player.setPad(awesomePad);

        Assertions.assertEquals(player.getPad(), awesomePad);
    }
}