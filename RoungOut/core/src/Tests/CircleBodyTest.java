package Tests;

import Model.GameObjects.CircleBody;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Ken Bäcklund
 */
class CircleBodyTest {

    private static final double THRESHOLD = 0.0001f;

    private static final double XPOS = 100f;
    private static final double YPOS = -100f;
    private static final double RADIUS = 10f;
    private static final double ANGLE = Math.PI / 2f;   // =45 degrees
    private static final double SPEED = 20f;

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
    void setRadius() {
        double expectedRadius = RADIUS + 1f;
        body.setRadius(expectedRadius);
        Assertions.assertEquals(expectedRadius, body.getRadius(), THRESHOLD);
    }

    @Test
    void setMaxSpeed() {
        body.setMaxSpeed(SPEED);
        body.setSpeed(SPEED + 10f);
        Assertions.assertEquals(SPEED, body.getSpeed(), THRESHOLD);
    }

    @Test
    void distanceCircleBody() {
        double otherRadius = RADIUS * 2f;
        double range = 10f*RADIUS;
        double stepX = RADIUS / 3f;
        double stepY = RADIUS / 5f;
        CircleBody someBody = new CircleBody(XPOS, YPOS, otherRadius, 0f, 0f);

        for (double dy = -range; dy < range; dy += stepY ) {
            for (double dx = -range; dx < range; dx += stepX ) {
                someBody.setPosition(body.getX() + dx, body.getY() + dy);

                double relDx = body.getX() - someBody.getX();
                double relDy = body.getY() - someBody.getY();
                double centerDistance = Math.sqrt(relDx*relDx + relDy*relDy);
                double expectedDistance = centerDistance - RADIUS - otherRadius;
                Assertions.assertEquals(expectedDistance, body.distance(someBody));

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