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
        Pad pad = player.getPad();
        double X = pad.getPadXPos()+pad.getPadSpeed();
        double Y = pad.getPadYPos()+pad.getPadSpeed();
        player.movePad();
        Assertions.assertEquals(pad.getPadXPos() , X);
        Assertions.assertEquals(pad.getPadYPos() , Y);
        System.out.println("The Coordinates moved");
    }
}
