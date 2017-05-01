package Tests;

import Model.GameObjects.CircleShape;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Ken Bäcklund
 */
class CircleShapeTest {

    private final static double THRESHOLD = 0.0001f;
    private final static double RADIUS = 10f;
    private final static double DIAMETER = 2f*RADIUS;

    private CircleShape cShape1;
    private CircleShape cShape2;


    @BeforeEach
    void setUp() {
        cShape1 = new CircleShape(-RADIUS);
        cShape2 = new CircleShape(cShape1);
    }

    @Test
    void getWidth() {
        Assertions.assertEquals(DIAMETER, cShape1.getWidth(), THRESHOLD);
        Assertions.assertEquals(DIAMETER, cShape2.getWidth(), THRESHOLD);
    }

    @Test
    void getHeight() {
        Assertions.assertEquals(DIAMETER, cShape1.getHeight(), THRESHOLD);
        Assertions.assertEquals(DIAMETER, cShape2.getHeight(), THRESHOLD);
    }

    @Test
    void getRadius() {
        Assertions.assertEquals(RADIUS, cShape1.getRadius(), THRESHOLD);
        Assertions.assertEquals(RADIUS, cShape2.getRadius(), THRESHOLD);
    }

}