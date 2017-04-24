package Tests;

import Objects.Pad;
import Objects.Player;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Created by Alex on 2017-04-24.
 * Generalised version of MovePadTest
 */
public class PadTest {

    Player player;
    float PadLength = 30;
    float PadWidth = 30;
    double PadXPos = 30;
    double PadYPos = 30;
    float PadSpeed = 30;

    @BeforeEach
    void setUp(){ player = new Player();}

    //Created by DukeA on 2017-04-04.
    //Modified by Alex on 2017-04-24
    @Test
    public void moveTest() {
        // At the moment hard coded
        // Get the next X coordinate
        double nextX = player.getPad().getPadXPos()
                + player.getPad().getPadSpeed();
        //Get the next Y Coordinate
        double nextY = player.getPad().getPadYPos()
                + player.getPad().getPadSpeed();

        player.movePad();

        Assertions.assertEquals(player.getPad().getPadXPos() , nextX);
        Assertions.assertEquals(player.getPad().getPadYPos() , nextY);
    }

    @Test
    public void getLength(){
        Assertions.assertEquals(player.getPad().getLength(),PadLength);
    }
}