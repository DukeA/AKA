package Tests;

import Objects.Pad;
import Objects.Player;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Created by Alex on 2017-04-24.
 * Generalised version of MovePadTest created by DukeA on 2017-04-04
 *
 * This is more of a player test than it is a pad test actually
 */
public class PlayerTest {

    Player player;
    //Values used for  these tests
    float PadLength = 12;
    float PadWidth = 30;
    double PadXPos = 100;
    double PadYPos = 100;
    float PadSpeed = 30;

    @BeforeEach
    void setUp(){ player = new Player(PadLength,PadWidth, PadXPos,PadYPos, PadSpeed);}

    @Test
    public void moveTest() {
        // Get the next X coordinate
        double nextX = player.getPad().getPadXPos()
                + player.getPad().getPadSpeed();
        //Get the next Y Coordinate
        double nextY = player.getPad().getPadYPos()
                + player.getPad().getPadSpeed();

        player.movePad();

        //Check that the pad has moved to the desired positions
        Assertions.assertEquals(player.getPad().getPadXPos() , nextX);
        Assertions.assertEquals(player.getPad().getPadYPos() , nextY);
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
}