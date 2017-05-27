package Tests;

import Model.GameObjects.Physics.RectangleBody;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * @author Ken Bäcklund
 */
class RectangleBodyTest {

    private static final float THRESHOLD = 0.0001f;

    private static final float XPOS = 100f;
    private static final float YPOS = -100f;
    private static final float WIDTH = 40f;
    private static final float HEIGHT = 20f;
    private static final float ANGLE = (float) Math.PI / 2f;   // =45 degrees
    private static final float SPEED = 10f;

    private RectangleBody body;



    @BeforeEach
    void setUp() {
        body = new RectangleBody(XPOS, YPOS, WIDTH, HEIGHT, ANGLE, SPEED);
    }

    @Test
    void getters() {
        Assertions.assertEquals(XPOS, body.getX(), THRESHOLD);
        Assertions.assertEquals(YPOS, body.getY(), THRESHOLD);
        Assertions.assertEquals(WIDTH, body.getWidth(), THRESHOLD);
        Assertions.assertEquals(HEIGHT, body.getHeight(), THRESHOLD);
    }

    @Test
    void setXandY() {
        body.setX(10);
        Assertions.assertEquals(10, body.getX(), THRESHOLD);

        body.setY(11);
        Assertions.assertEquals(11, body.getY(), THRESHOLD);

        float expectedX = XPOS + 10f;
        float expectedY = YPOS - 10f;
        body.setPosition(expectedX, expectedY);
        Assertions.assertEquals(expectedX, body.getX(), THRESHOLD);
        Assertions.assertEquals(expectedY, body.getY(), THRESHOLD);
    }

    @Test
    void setSize() {
        float expectedX = WIDTH + 10f;
        float expectedY = HEIGHT - 10f;
       // body.setSize(expectedX, expectedY); //this method doesent exist anymore
        body.setWidth(expectedX);
        body.setHeight(expectedY);
        Assertions.assertEquals(expectedX, body.getWidth(), THRESHOLD);
        Assertions.assertEquals(expectedY, body.getHeight(), THRESHOLD);
    }

    @Test
    void distanceTwoRectangles() {

        float otherWidth = HEIGHT;     // Same dimensions with a 90 degree turn
        float otherHeight = WIDTH;

        float range = 2f*(WIDTH + HEIGHT);

        RectangleBody other = new RectangleBody(0f, 0f, otherWidth, otherHeight);
        body.setPosition(0f, 0f);

        for (float ry = -range; ry < range; ry++) {
            for (float rx = -range; rx < range; rx++) {
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

}