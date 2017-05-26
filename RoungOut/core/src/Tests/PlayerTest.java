package Tests;

import Model.GameObjects.Pad;
import Model.GameObjects.Player;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

/**
 * Created by Alex on 2017-04-24.
 * <p>
 * This is more of a player test than it is a pad test actually
 */
public class PlayerTest {

    Player player;
    //Values used for  these tests
    float PadLength = 12;
    float PadWidth = 30;
    float originX = 0;
    float originY = 0;
    float PadXPos = 100;
    float PadYPos = 100;
    float PadSpeed = 30;

    @BeforeEach
    void setUp() {
        player = new Player(PadLength, PadWidth, originX, originY, PadXPos, PadYPos, PadSpeed);
    }

    @Test
    public void playerMoveTestRight() {
        //Get the current X and Y pos
        float oldX = player.getPad().getPadXPos();
        float oldY = player.getPad().getPadYPos();

        double rot = 1.0/180.0*Math.PI;
        double cs = Math.cos(rot);
        double sn = Math.sin(rot);

        double translated_x = oldX - originX;
        double translated_y = oldY - originY;

        double result_x = translated_x * cs - translated_y * sn;
        double result_y = translated_x * sn + translated_y * cs;

        result_x += originX;
        result_y += originY;

        // Get the next X and Y coordinates
        float nextX = (float) result_x ;
        float nextY = (float) result_y ;

        player.moveRight();

        //Check that the pad has moved to the desired positions
        //Similar test is in the test for Pad
        Assertions.assertEquals(player.getPad().getPadXPos(), nextX);
        Assertions.assertEquals(player.getPad().getPadYPos(), nextY);

    }

    @Test
    public void playerMoveTestLeft() {
        float oldX = player.getPad().getPadXPos();
        float oldY = player.getPad().getPadYPos();

        double rot = -1.0/180.0*Math.PI;
        double cs = Math.cos(rot);
        double sn = Math.sin(rot);

        double translated_x = oldX - originX;
        double translated_y = oldY - originY;

        double result_x = translated_x * cs - translated_y * sn;
        double result_y = translated_x * sn + translated_y * cs;



        float nextX =(float)result_x;
        float nextY =(float)result_y;

        player.moveLeft();

        Assertions.assertEquals(player.getPad().getPadXPos(), nextX);
        Assertions.assertEquals(player.getPad().getPadYPos(), nextY);


    }


    @Test
    public void defaultConstructor() {
        Player player2 = new Player();
        //Verifying default by checking its position, position 0,0 is considered default
        Assertions.assertEquals(player2.getPad().getPadXPos(), 0);
        Assertions.assertEquals(player2.getPad().getPadYPos(), 0);
    }

    @Test
    public void setCordinates() {
        float x = 100;
        float y = 100;
        player.setPadCoordinates(x, y);

        Assertions.assertEquals(player.getPad().getPadXPos(), x);
        Assertions.assertEquals(player.getPad().getPadYPos(), y);
    }

    @Test
    public void getCordinates() {
        ArrayList<Float> cords = new ArrayList<Float>();
        cords.add(0, PadXPos);
        cords.add(1, PadYPos);

        Assertions.assertEquals(player.getPadCoordinates(), cords);
    }

    @Test
    public void points() {
        Assertions.assertEquals(player.getPoints(), 0); //0 is default
        player.setPoints(10);
        Assertions.assertEquals(player.getPoints(), 10); //10 is the value we set it to

        player.offsetPoints();
        Assertions.assertEquals(player.getPoints(), 11); //10 + 1

        player.offsetPoints(10);
        Assertions.assertEquals(player.getPoints(), 21); //10 + 1 + 10
    }

    @Test
    void setPad() {
        Pad awesomePad = new Pad(100, 10, 0, 0, 42, 42, 100);
        player.setPad(awesomePad);

        Assertions.assertEquals(player.getPad(), awesomePad);
    }
}