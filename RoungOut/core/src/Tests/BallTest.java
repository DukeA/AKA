package Tests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import Model.GameObjects.Ball;

/**
 * @author Ken Bäcklund
 */
public class BallTest {

    private Ball ball;

    @BeforeEach
    public void beforeEach() {
        // Position (100, 100)
        // Radius 2.0
        // Degrees 30.0
        // Speed 10.0
        ball = new Ball(1000.0f, -2000.0f, 2.0f, 30.0f, 100.00f);
    }



    // Test getting the ball's X position.
    @Test
    public void testGetX() throws Exception {
        Assertions.assertEquals(ball.getX(), 1000.0f, 0.0001f);
    }

    // Test setting the ball's X position.
    @Test
    public void testSetX() throws Exception {
        Assertions.assertEquals(ball.getX(), 1000.0f, 0.0001f);
        ball.setX(-1000.0);
        Assertions.assertEquals(ball.getX(), -1000.0f, 0.0001f);
    }

    // Test getting the ball's Y position.
    @Test
    public void testGetY() throws Exception {
        Assertions.assertEquals(ball.getY(), -2000.0f, 0.0001f);
    }

    // Test setting the ball's Y position.
    @Test
    public void testSetY() throws Exception {
        Assertions.assertEquals(ball.getY(), -2000.0f, 0.0001f);
        ball.setY(2000.0);
        Assertions.assertEquals(ball.getY(), 2000.0f, 0.0001f);
    }

    // Test setting the ball's X and Y position.
    @Test
    public void testSetPosition() throws Exception {
        Assertions.assertEquals(ball.getX(), 1000.0f, 0.0001f);
        Assertions.assertEquals(ball.getY(), -2000.0f, 0.0001f);
        ball.setPosition(-1000.0f, 2000.0f);
        Assertions.assertEquals(ball.getX(), -1000.0f, 0.0001f);
        Assertions.assertEquals(ball.getY(), 2000.0f, 0.0001f);
    }

    // Test getting the ball's radius.
    @Test
    public void testGetRadius() throws Exception {
        Assertions.assertEquals(ball.getRadius(), 2.00f, 0.0001f);
    }

    // Test setting the ball's radius.
    @Test
    public void testSetRadius() throws Exception {
        Assertions.assertEquals(ball.getRadius(), 2.00f, 0.0001f);
        ball.setRadius(3.00);
        Assertions.assertEquals(ball.getRadius(), 3.00f, 0.0001f);
        ball.setRadius(-4.00);
        Assertions.assertEquals(ball.getRadius(), 4.00f, 0.0001f);
    }

    // Test getting the ball's current speed.
    @Test
    public void testGetSpeed() throws Exception {
        Assertions.assertEquals(ball.getSpeed(), 100.00f, 0.0001f);
    }

    // Test setting the ball's speed.
    @Test
    public void testSetSpeed() throws Exception {
        Assertions.assertEquals(ball.getSpeed(), 100.00f, 0.0001f);
        ball.setSpeed(200.00);
        Assertions.assertEquals(ball.getSpeed(), 200.00f, 0.0001f);
        ball.setSpeed(-300.00);
        Assertions.assertEquals(ball.getSpeed(), 300.00f, 0.0001f);
    }

    // Test getting the ball's current direction in degrees.
    @Test
    public void testGetDegrees() throws Exception {
        Assertions.assertEquals(ball.getDegrees(), 30.00f, 0.0001f);
    }

    // Test setting the ball's direction in degrees.
    @Test
    public void testSetDegrees() throws Exception {
        Assertions.assertEquals(ball.getDegrees(), 30.00f, 0.0001f);
        ball.setDegrees(90.00f);
        Assertions.assertEquals(ball.getDegrees(), 90.00f, 0.0001f);
        ball.setDegrees(720.00f);
        Assertions.assertEquals(ball.getDegrees(), 0.00f, 0.0001f);
        ball.setDegrees(-90.00f);
        Assertions.assertEquals(ball.getDegrees(), 270.00f, 0.0001f);
        // Also test if a negative speed will set ball in opposite direction
        ball.setSpeed(-10.00f);
        Assertions.assertEquals(ball.getDegrees(), 90.00f, 0.0001f);
    }

    // Test getting the ball's delta-X position.
    @Test
    public void testGetDeltaX() throws Exception {
        ball.setDegrees(0.0f);    // Cos(0') = 1
        Assertions.assertEquals(ball.getDeltaX(), 100.00f, 0.0001f);
        ball.setDegrees(-180.0f);    // Cos(-180') = -1
        Assertions.assertEquals(ball.getDeltaX(), -100.00f, 0.0001f);
    }

    // Test getting the ball's delta-Y position.
    @Test
    public void testGetDeltaY() throws Exception {
        ball.setDegrees(30.0f);    // Sin(30') = 0.5
        Assertions.assertEquals(ball.getDeltaY(), 50.00f, 0.0001f);
        ball.setDegrees(-30.0f);    // Sin(-30') = -0.5
        Assertions.assertEquals(ball.getDeltaY(), -50.00f, 0.0001f);
    }

    // Test moving the ball
    @Test
    public void testMoveBall() throws Exception {

        double speed = 500.0f;
        for (float degrees = -720.0f; degrees < 720.0f; degrees+=0.5f) {
            ball.setSpeed(speed);
            ball.setDegrees(degrees);

            double expectedX = ball.getX() + speed * Math.cos( Math.toRadians(degrees) );
            double expectedY = ball.getY() + speed * Math.sin( Math.toRadians(degrees) );
            ball.moveBall();

            Assertions.assertEquals(ball.getX(), expectedX, 0.0001f);
            Assertions.assertEquals(ball.getY(), expectedY, 0.0001f);
        }

    }



}