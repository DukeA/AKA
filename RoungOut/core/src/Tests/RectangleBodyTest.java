package Tests;

import Model.GameObjects.RectangleBody;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Created by kendu on 2017-04-30.
 */
class RectangleBodyTest {

    private static final double THRESHOLD = 0.0001f;

    private static final double XPOS = 100f;
    private static final double YPOS = -100f;
    private static final double WIDTH = 40f;
    private static final double HEIGHT = 20f;
    private static final double ANGLE = Math.PI / 2f;   // =45 degrees
    private static final double SPEED = 10f;

    private RectangleBody body;



    @BeforeEach
    void setUp() {
        body = new RectangleBody(XPOS, YPOS, WIDTH, HEIGHT, ANGLE, SPEED);
    }

    @Test
    void getX() {
        Assertions.assertEquals(XPOS, body.getX(), THRESHOLD);
    }

    @Test
    void getY() {
        Assertions.assertEquals(YPOS, body.getY(), THRESHOLD);
    }

    @Test
    void getWidth() {
        Assertions.assertEquals(WIDTH, body.getWidth(), THRESHOLD);
    }

    @Test
    void getHeight() {
        Assertions.assertEquals(HEIGHT, body.getHeight(), THRESHOLD);
    }

    @Test
    void getAngle() {
        double expectedAngle = (ANGLE + 8f*Math.PI) % (2f*Math.PI);
        Assertions.assertEquals(expectedAngle, body.getAngle(), THRESHOLD);
    }

    @Test
    void getSpeed() {
        Assertions.assertEquals(SPEED, body.getSpeed(), THRESHOLD);
    }

    @Test
    void setX() {
        double expectedX = XPOS + 10f;
        body.setX(expectedX);
        Assertions.assertEquals(expectedX, body.getX(), THRESHOLD);
    }

    @Test
    void setY() {
        double expectedY = YPOS - 10f;
        body.setY(expectedY);
        Assertions.assertEquals(expectedY, body.getY(), THRESHOLD);
    }

    @Test
    void setPosition() {
        double expectedX = XPOS + 10f;
        double expectedY = YPOS - 10f;
        body.setPosition(expectedX, expectedY);
        Assertions.assertEquals(expectedX, body.getX(), THRESHOLD);
        Assertions.assertEquals(expectedY, body.getY(), THRESHOLD);
    }

    @Test
    void setSize() {
        double expectedX = WIDTH + 10f;
        double expectedY = HEIGHT - 10f;
        body.setSize(expectedX, expectedY);
        Assertions.assertEquals(expectedX, body.getWidth(), THRESHOLD);
        Assertions.assertEquals(expectedY, body.getHeight(), THRESHOLD);
    }

    @Test
    void setAngle() {
        double maxAngle = 2f*Math.PI;
        double minAngle = -maxAngle;
        for (double a = minAngle; a < maxAngle; a += maxAngle/32f) {
            double expectedAngle = (a + 8f*Math.PI) % (2f*Math.PI);
            body.setAngle(a);
            Assertions.assertEquals(expectedAngle, body.getAngle(), THRESHOLD);
        }
    }

    @Test
    void setSpeed() {
        double expectedSpeed = SPEED + 100f;
        body.setSpeed(expectedSpeed);
        Assertions.assertEquals(expectedSpeed, body.getSpeed(), THRESHOLD);
    }

    @Test
    void setMaxSpeed() {
        body.setMaxSpeed(SPEED);
        body.setSpeed(SPEED + 10f);
        Assertions.assertEquals(SPEED, body.getSpeed(), THRESHOLD);
    }

    @Test
    void distanceTwoRectangles() {

        double otherWidth = HEIGHT;     // Same dimensions with a 90 degree turn
        double otherHeight = WIDTH;

        double range = 2f*(WIDTH + HEIGHT);

        RectangleBody other = new RectangleBody(0f, 0f, otherWidth, otherHeight);
        body.setPosition(0f, 0f);

        System.out.printf("%.2f   %.2f\n", body.distance(other), other.distance(body));

        for (double ry = -range; ry < range; ry++) {
            for (double rx = -range; rx < range; rx++) {
                other.setPosition(rx, ry);

            }
        }

    }

    @Test
    void distanceOtherBody() {
        // TODO:  Measure distance to Body of different type (ie. CircleBody)
        // CircleBody cBody = new CircleBody(cXPos, cYPos, cRadius, cAngle, cSpeed);
        //fail("Tests.distanceCircleRectangle() not yet implemented.");
    }

    @Test
    void move() {
        for (double a = -2f*Math.PI; a < 2f*Math.PI; a += Math.PI/64f) {
            double expectedX = body.getX() + Math.cos(ANGLE) * SPEED;
            double expectedY = body.getY() + Math.sin(ANGLE) * SPEED;
            body.move();
            Assertions.assertEquals(expectedX, body.getX(), THRESHOLD);
            Assertions.assertEquals(expectedY, body.getY(), THRESHOLD);
        }
    }

}