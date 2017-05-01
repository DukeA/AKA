package Tests;

import Model.GameObjects.Brick;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * @author Ken Bäcklund
 */
public class BrickTest {

    private static final double THRESHOLD = 0.0001f;

    private static final double XPOS = 0f;
    private static final double YPOS = 0f;
    private static final double WIDTH = 30f;
    private static final double HEIGHT = 20f;
    private static final double ANGLE = Math.PI / 2f;   // =45 degrees
    private static final double SPEED = 10f;

    private Brick brick;



    @BeforeEach
    void setUp() {
        brick = new Brick(XPOS, YPOS, WIDTH, HEIGHT);
    }

    @Test
    void getX() {
        Assertions.assertEquals(XPOS, brick.getX(), THRESHOLD);
    }

    @Test
    void getY() {
        Assertions.assertEquals(YPOS, brick.getY(), THRESHOLD);
    }

    @Test
    void setX() {
        double expectedX = XPOS + 10f;
        brick.setX(expectedX);
        Assertions.assertEquals(expectedX, brick.getX(), THRESHOLD);
    }

    @Test
    void setY() {
        double expectedY = YPOS - 10f;
        brick.setY(expectedY);
        Assertions.assertEquals(expectedY, brick.getY(), THRESHOLD);
    }

    @Test
    void setPosition() {
        double expectedX = XPOS + 10f;
        double expectedY = YPOS - 10f;
        brick.setPosition(expectedX, expectedY);
        Assertions.assertEquals(expectedX, brick.getX(), THRESHOLD);
        Assertions.assertEquals(expectedY, brick.getY(), THRESHOLD);
    }

}
