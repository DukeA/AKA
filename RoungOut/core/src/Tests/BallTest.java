package Tests;

import Model.GameObjects.Ball;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Created by kendu on 2017-04-29.
 */
class BallTest {

    private final static double THRESHOLD = 0.0001f;

    private final static double XPOS = 100.0f;
    private final static double YPOS = -100.0f;
    private final static double RADIUS = 10f;

    private Ball ball;

    @BeforeEach
    void setUp() {
        ball = new Ball(XPOS, YPOS, RADIUS);
    }

    @Test
    void getX() {
        Assertions.assertEquals(XPOS, ball.getX(), THRESHOLD);
    }

    @Test
    void getY() {
        Assertions.assertEquals(YPOS, ball.getY(), THRESHOLD);
    }

    @Test
    void setX() {
        double expectedX = XPOS + 1f;
        ball.setX(expectedX);
        Assertions.assertEquals(expectedX, ball.getX(), THRESHOLD);
    }

    @Test
    void setY() {
        double expectedY = YPOS - 1f;
        ball.setY(expectedY);
        Assertions.assertEquals(expectedY, ball.getY(), THRESHOLD);
    }

    @Test
    void setPosition() {
        double expectedX = XPOS + 1f;
        double expectedY = YPOS - 1f;
        ball.setPosition(expectedX, expectedY);
        Assertions.assertEquals(expectedX, ball.getX(), THRESHOLD);
        Assertions.assertEquals(expectedY, ball.getY(), THRESHOLD);
    }

    @Test
    void move() {
        // TODO: Add Ball movement
    }

}