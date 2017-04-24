package Tests;

import Objects.Pad;
import Objects.Player;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * Created by DukeA on 2017-04-04.
 */
public class MovePadTest {

    @Test
    public void moveTest() {
        Player player = new Player();
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
}
