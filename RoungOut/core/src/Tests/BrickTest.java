package Tests;

import Objects.Brick;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Ken Bäcklund
 */
class BrickTest {

    private Brick brick;
    private final double brickX = 100.0f;
    private final double brickY = 200.0f;
    private final double brickWidth = 300.0f;
    private final double brickHeight = 400.0f;

    private final double threshold = 0.0001f;

    @BeforeEach
    void setUp() {
        brick = new Brick(brickX, brickY, brickWidth, brickHeight);
    }

    @Test
    void testGetX() {
        Assertions.assertEquals(brick.getX(), brickX, threshold);
    }

    @Test
    void testSetX() {
        brick.setX(-10.0f);
        Assertions.assertEquals(brick.getX(), -10.0f, threshold);
    }

    @Test
    void testGetY() {
        Assertions.assertEquals(brick.getY(), brickY, threshold);
    }

    @Test
    void testSetY() {
        brick.setY(20.0f);
        Assertions.assertEquals(brick.getY(), 20.0f, threshold);
    }

    @Test
    void testSetPosition() {
        brick.setPosition(-50.0f, 50.0f);
        Assertions.assertEquals(brick.getX(), -50.0f, threshold);
        Assertions.assertEquals(brick.getY(), 50.0f, threshold);
    }

    @Test
    void testGetWidth() {
        Assertions.assertEquals(brick.getWidth(), brickWidth, threshold);
    }

    @Test
    void testSetWidth() {
        brick.setWidth(100.0f);
        Assertions.assertEquals(brick.getWidth(), 100.0f, threshold);
    }

    @Test
    void testSetBadWidth() {
        try {
            brick.setWidth(-100.0f);
            Assertions.fail("Didn't raise IllegalArgumentException.");
        }
        catch (IllegalArgumentException e) {
            // As expected.
        }
        Assertions.assertEquals(brick.getWidth(), brickWidth, threshold);   // Unmodified.
    }

    @Test
    void testGetHeight() {
        Assertions.assertEquals(brick.getHeight(), brickHeight, threshold);
    }

    @Test
    void testSetHeight() {
        brick.setHeight(300.0f);
        Assertions.assertEquals(brick.getHeight(), 300.0f, threshold);
    }

    @Test
    void testSetBadHeight() {
        try {
            brick.setHeight(0.0f);
            Assertions.fail("Didn't raise IllegalArgumentException.");
        }
        catch (IllegalArgumentException e) {
            // As expected.
        }
        Assertions.assertEquals(brick.getHeight(), brickHeight, threshold);   // Unmodified.
    }

    @Test
    void testSetSize() {
        brick.setSize(0.1f, 0.2f);
        Assertions.assertEquals(brick.getWidth(), 0.1f, threshold);
        Assertions.assertEquals(brick.getHeight(), 0.2f, threshold);
    }

}