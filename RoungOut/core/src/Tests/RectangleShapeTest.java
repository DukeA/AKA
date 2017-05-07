package Tests;

import Model.GameObjects.Physics.RectangleShape;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * @author Ken Bäcklund
 */
class RectangleShapeTest {

    private final static double THRESHOLD = 0.0001f;

    private final static double WIDTH = 40f;
    private final static double HEIGHT = 20f;

    private RectangleShape rShape1;
    private RectangleShape rShape2;

    @BeforeEach
    void setUp() {
        rShape1 = new RectangleShape(WIDTH, HEIGHT);
        rShape2 = new RectangleShape(rShape1);
    }
    @Test
    void getters() {
        Assertions.assertEquals(WIDTH, rShape1.getWidth(), THRESHOLD);
        Assertions.assertEquals(WIDTH, rShape2.getWidth(), THRESHOLD);

        Assertions.assertEquals(HEIGHT, rShape1.getHeight(), THRESHOLD);
        Assertions.assertEquals(HEIGHT, rShape2.getHeight(), THRESHOLD);
    }

}
