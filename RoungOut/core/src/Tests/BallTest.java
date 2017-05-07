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

    private final static float THRESHOLD = 0.0001f;

    private final static float XPOS = 100.0f;
    private final static float YPOS = -100.0f;
    private final static float RADIUS = 10f;
    private final static float ANGLE = (float) (4f*Math.PI);
    private final static float SPEED = 20.0f;

    private Ball ball;

    @BeforeEach
    void setUp() {
        ball = new Ball(XPOS, YPOS, RADIUS, ANGLE, SPEED);
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
    void getAngle() {
        double expectedAngle = (ANGLE + 8f*Math.PI) % (2f*Math.PI);
        Assertions.assertEquals(expectedAngle, ball.getAngle(), THRESHOLD);
    }

    @Test
    void getSpeed() {
        Assertions.assertEquals(SPEED, ball.getSpeed(), THRESHOLD);
    }

    @Test
    void setX() {
        float expectedX = XPOS + 1f;
        ball.setX(expectedX);
        Assertions.assertEquals(expectedX, ball.getX(), THRESHOLD);
    }

    @Test
    void setY() {
        float expectedY = YPOS - 1f;
        ball.setY(expectedY);
        Assertions.assertEquals(expectedY, ball.getY(), THRESHOLD);
    }

    @Test
    void setPosition() {
        float expectedX = XPOS + 1f;
        float expectedY = YPOS - 1f;
        ball.setPosition(expectedX, expectedY);
        Assertions.assertEquals(expectedX, ball.getX(), THRESHOLD);
        Assertions.assertEquals(expectedY, ball.getY(), THRESHOLD);
    }

    @Test
    void setAngle() {
        float maxAngle = (float) (4f*Math.PI);
        float minAngle = -maxAngle;
        for (float a = minAngle; a < maxAngle; a += maxAngle/64f) {
            float expectedAngle = (float) ((a + 8f*Math.PI) % (2f*Math.PI));
            ball.setAngle(a);
            Assertions.assertEquals(expectedAngle, ball.getAngle(), THRESHOLD);
        }
    }

    @Test
    void setSpeed() {
        float expectedSpeed = SPEED + 100f;
        ball.setSpeed(expectedSpeed);
        Assertions.assertEquals(expectedSpeed, ball.getSpeed(), THRESHOLD);
    }

    @Test
    void setMaxSpeed() {
        float expectedSpeed = SPEED;
        ball.setMaxSpeed(expectedSpeed);
        ball.setSpeed(expectedSpeed + 100f);
        Assertions.assertEquals(expectedSpeed, ball.getSpeed(), THRESHOLD);
    }

    @Test
    void move() {
        for (double a = -2f*Math.PI; a < 2f*Math.PI; a += Math.PI/64f) {
            double expectedX = ball.getX() + Math.cos(ANGLE) * SPEED;
            double expectedY = ball.getY() + Math.sin(ANGLE) * SPEED;
            ball.move();
            Assertions.assertEquals(expectedX, ball.getX(), THRESHOLD);
            Assertions.assertEquals(expectedY, ball.getY(), THRESHOLD);
        }
    }

}