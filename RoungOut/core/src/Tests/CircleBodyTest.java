package Tests;

import Model.GameObjects.Physics.CircleBody;
import Model.GameObjects.Physics.RectangleBody;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Ken Bäcklund
 */
class CircleBodyTest {

    private static final float THRESHOLD = 0.0001f;

    private static final float XPOS = 100f;
    private static final float YPOS = -100f;
    private static final float RADIUS = 10f;
    private static final float ANGLE = (float) Math.PI / 2f;   // =45 degrees
    private static final float SPEED = 20f;

    private CircleBody body;

    @BeforeEach
    void setUp() {
        body = new CircleBody(XPOS, YPOS, RADIUS, ANGLE, SPEED);
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
    void getRadius() {
        Assertions.assertEquals(RADIUS, body.getRadius(), THRESHOLD);
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
        float expectedX = XPOS + 10f;
        body.setX(expectedX);
        Assertions.assertEquals(expectedX, body.getX(), THRESHOLD);
    }

    @Test
    void setY() {
        float expectedY = YPOS - 10f;
        body.setY(expectedY);
        Assertions.assertEquals(expectedY, body.getY(), THRESHOLD);
    }

    @Test
    void setPosition() {
        float expectedX = XPOS + 10f;
        float expectedY = YPOS - 10f;
        body.setPosition(expectedX, expectedY);
        Assertions.assertEquals(expectedX, body.getX(), THRESHOLD);
        Assertions.assertEquals(expectedY, body.getY(), THRESHOLD);
    }

    @Test
    void setAngle() {
        float maxAngle = (float) (2f*Math.PI);
        float minAngle = -maxAngle;
        for (float a = minAngle; a < maxAngle; a += maxAngle/32f) {
            float expectedAngle = (float) ((a + 8f*Math.PI) % (2f*Math.PI));
            body.setAngle(a);
            Assertions.assertEquals(expectedAngle, body.getAngle(), THRESHOLD);
        }
    }

    @Test
    void setSpeed() {
        float expectedSpeed = SPEED + 100f;
        body.setSpeed(expectedSpeed);
        Assertions.assertEquals(expectedSpeed, body.getSpeed(), THRESHOLD);
    }

    @Test
    void setRadius() {
        float expectedRadius = RADIUS + 1f;
        body.setWidth(expectedRadius*2); //setWidth on a circleBody is equvivalent to seting the diameter
        Assertions.assertEquals(expectedRadius, body.getRadius(), THRESHOLD);
    }

    @Test
    void setMaxSpeed() {
        body.setMaxSpeed(SPEED);
        body.setSpeed(SPEED + 10f);
        Assertions.assertEquals(SPEED, body.getSpeed(), THRESHOLD);
    }

    @Test
    void distanceTwoCircles() {
        float otherRadius = RADIUS * 2f;
        float range = 10f*RADIUS;
        float stepX = RADIUS / 3f;
        float stepY = RADIUS / 5f;
        CircleBody someBody = new CircleBody(XPOS, YPOS, otherRadius, 0f, 0f);

        for (float dy = -range; dy < range; dy += stepY ) {
            for (float dx = -range; dx < range; dx += stepX ) {
                someBody.setPosition(body.getX() + dx, body.getY() + dy);

                float relDx = body.getX() - someBody.getX();
                float relDy = body.getY() - someBody.getY();
                float centerDistance = (float) Math.sqrt(relDx*relDx + relDy*relDy);
                float expectedDistance = Math.max(0, centerDistance - RADIUS - otherRadius);
                System.out.print("Expected: " + expectedDistance +" ");
                System.out.println("Reality: " + body.distance(someBody));
                Assertions.assertEquals(expectedDistance, body.distance(someBody));

            }
        }
    }

    @Test
    void distanceCircleRectangle() {

        float cRadius = 10f;
        float rWidth = cRadius*2f;
        float rHeight = cRadius*3f;
        float range = cRadius*5f;

        CircleBody    cir = new CircleBody   (0f, 0f, cRadius);
        RectangleBody rec = new RectangleBody(0f, 0f, rWidth, rHeight);

        for (float ry = -range; ry < range; ry++) {
            for (float rx = -range; rx < range; rx++) {
                rec.setPosition(rx, ry);

                float dx = Math.max(0f, Math.abs(cir.getX() - rec.getX()) - rec.getWidth()/2f);
                float dy = Math.max(0f, Math.abs(cir.getY() - rec.getY()) - rec.getHeight()/2f);
                float expectedDistance = (float) Math.max(0, Math.sqrt(dx*dx+dy*dy) - cir.getRadius());
                Assertions.assertEquals(expectedDistance, cir.distance(rec), THRESHOLD);
                Assertions.assertEquals(expectedDistance, rec.distance(cir), THRESHOLD);

            }
        }
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
