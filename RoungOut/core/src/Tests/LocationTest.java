package Tests;

import Model.GameObjects.Physics.Location;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * @author Ken Bäcklund
 */
class LocationTest {

    private Location loc;

    private final static float XPOS = 100.0f;
    private final static float YPOS = -100.0f;
    private final static float ANGLE = (float) (4f*Math.PI);
    private final static float SPEED = 10.0f;

    private final static double THRESHOLD = 0.0001f;


    @BeforeEach
    void setUp() {
        loc = new Location(XPOS, YPOS, ANGLE, SPEED);
    }

    @Test
    void getters() {
        Assertions.assertEquals(XPOS, loc.getX(), THRESHOLD);
        Assertions.assertEquals(YPOS, loc.getY(), THRESHOLD);

        Assertions.assertEquals(SPEED, loc.getSpeed(), THRESHOLD);

        double expectedAngle = (ANGLE + 8f*Math.PI) % (2f*Math.PI);
        Assertions.assertEquals(expectedAngle, loc.getAngle(), THRESHOLD);

        double expectedDX = SPEED * Math.cos(ANGLE);
        Assertions.assertEquals(expectedDX, loc.getDeltaX(), THRESHOLD);

        double expectedDY = SPEED * Math.sin(ANGLE);
        Assertions.assertEquals(expectedDY, loc.getDeltaY(), THRESHOLD);
    }

    @Test
    void setXandY() {
        loc.setX(10);
        Assertions.assertEquals(10, loc.getX(), THRESHOLD);

        loc.setY(11);
        Assertions.assertEquals(11, loc.getY(), THRESHOLD);

        float expectedX = XPOS + 1f;
        float expectedY = YPOS - 1f;
        loc.setPosition(expectedX, expectedY);
        Assertions.assertEquals(expectedX, loc.getX(), THRESHOLD);
        Assertions.assertEquals(expectedY, loc.getY(), THRESHOLD);
    }

    @Test
    void setSpeed() {
        // Delta values are also affected by speed change
        float expectedSpeed = SPEED + 100f;
        double expectedDX = Math.cos(ANGLE) * expectedSpeed;
        double expectedDY = Math.sin(ANGLE) * expectedSpeed;
        loc.setSpeed(expectedSpeed);
        Assertions.assertEquals(expectedSpeed, loc.getSpeed(), THRESHOLD);
        Assertions.assertEquals(expectedDX, loc.getDeltaX(), THRESHOLD);
        Assertions.assertEquals(expectedDY, loc.getDeltaY(), THRESHOLD);
    }

    @Test
    void setMaxSpeed() {
        float expectedSpeed = SPEED;
        loc.setMaxSpeed(expectedSpeed);
        loc.setSpeed(expectedSpeed + 100f);
        Assertions.assertEquals(expectedSpeed, loc.getMaxSpeed(), THRESHOLD);
        Assertions.assertEquals(expectedSpeed, loc.getSpeed(), THRESHOLD);
        try {
            loc.setMaxSpeed(-1f); // Should throw IllegalArgumentException.
            Assertions.fail("Didn't throw IllegalArgumentException.");
        }
        catch (IllegalArgumentException e) {
            // Speed should be unaffected.
            Assertions.assertEquals(expectedSpeed, loc.getSpeed(), THRESHOLD);
        }
    }

    @Test
    void setAngle() {
        double maxAngle = 4f*Math.PI;
        double minAngle = -maxAngle;
        for (float a = (float) minAngle; a < maxAngle; a += maxAngle/64f) {
            double expectedAngle = (a + 8f*Math.PI) % (2f*Math.PI);
            loc.setAngle(a);
            Assertions.assertEquals(expectedAngle, loc.getAngle(), THRESHOLD);
        }
    }

    @Test
    void move() {
        for (double a = -2f*Math.PI; a < 2f*Math.PI; a += Math.PI/64f) {
            double expectedX = loc.getX() + Math.cos(ANGLE) * SPEED;
            double expectedY = loc.getY() + Math.sin(ANGLE) * SPEED;
            loc.move();
            Assertions.assertEquals(expectedX, loc.getX(), THRESHOLD);
            Assertions.assertEquals(expectedY, loc.getY(), THRESHOLD);
        }
    }

    @Test
    void distance() {
        //Location loc2 = new Location(loc);
        Location loc2 = new Location(XPOS, YPOS, ANGLE, SPEED);
        double expectedDistance1 = 0f;
        double expectedDistance2 = Math.sqrt(
                loc.getDeltaX()*loc.getDeltaX() + loc.getDeltaY()*loc.getDeltaY() );
        Assertions.assertEquals(expectedDistance1, loc.distance(loc2), THRESHOLD);
        loc2.move();
        Assertions.assertEquals(expectedDistance2, loc.distance(loc2), THRESHOLD);
    }

}
