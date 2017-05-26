package Tests;

import Model.GameObjects.Ball;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

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
    void getters() {
        Assertions.assertEquals(XPOS, ball.getX(), THRESHOLD);
        Assertions.assertEquals(YPOS, ball.getY(), THRESHOLD);

        //GetAngle
        double expectedAngle = (ANGLE + 8f*Math.PI) % (2f*Math.PI);
        Assertions.assertEquals(expectedAngle, ball.getDirection(), THRESHOLD);

        //GetSpeed
        Assertions.assertEquals(SPEED, ball.getSpeed(), THRESHOLD);
    }

    @Test
    void setXandY() {
        ball.setX(10);
        Assertions.assertEquals(10, ball.getX(), THRESHOLD);

        ball.setY(11);
        Assertions.assertEquals(11, ball.getY(), THRESHOLD);

        ball.setPosition(20, 21);
        Assertions.assertEquals(20, ball.getX(), THRESHOLD);
        Assertions.assertEquals(21, ball.getY(), THRESHOLD);
    }

    @Test
    void setAngle() {
        float maxAngle = (float) (4f*Math.PI);
        float minAngle = -maxAngle;
        for (float a = minAngle; a < maxAngle; a += maxAngle/64f) {
            float expectedAngle = (float) ((a + 8f*Math.PI) % (2f*Math.PI));
            ball.setDirection(a);
            Assertions.assertEquals(expectedAngle, ball.getDirection(), THRESHOLD);
        }
    }

    @Test
    void setSpeeds() {
        ball.setSpeed(10);
        Assertions.assertEquals(10, ball.getSpeed(), THRESHOLD);

        //Test so we cant set a speed higher than the MaxSpeed
        ball.setMaxSpeed(20);
        ball.setSpeed(20 + 100f);
        Assertions.assertEquals(20, ball.getSpeed(), THRESHOLD);
    }

    @Test
    void move() {
        for (double a = -2f*Math.PI; a < 2f*Math.PI; a += Math.PI/64f) {
            double expectedX = ball.getX() + Math.cos(ANGLE) * SPEED;
            double expectedY = ball.getY() + Math.sin(ANGLE) * SPEED;
            ball.move(1);
            Assertions.assertEquals(expectedX, ball.getX(), THRESHOLD);
            Assertions.assertEquals(expectedY, ball.getY(), THRESHOLD);
        }
    }

}