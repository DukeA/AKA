import javafx.geometry.Point2D;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author Ken Bäcklund
 */
public class BallTest {

    @Test
    /**
     * Test: Point2D getPosition()
     * Assert that the correct ball position is returned.
     */
    public void getPosition() throws Exception {
        Point2D expectedPosition = new Point2D(100,200);
        Ball ball = new Ball(expectedPosition, new Point2D(0, 0), 10.0f);
        Point2D curPosition = ball.getPosition();
        Assert.assertEquals(curPosition.getX(), expectedPosition.getX(), 0.01);
        Assert.assertEquals(curPosition.getY(), expectedPosition.getY(), 0.01);
    }

    @Test
    /**
     * Test: Void setPosition(Point2D position)
     * Assert that the correct ball position is set.
     */
    public void setPosition() throws Exception {
        Point2D expectedPosition = new Point2D(300,400);
        Ball ball = new Ball(new Point2D(0,0), new Point2D(0, 0), 10.0f);
        ball.setPosition(expectedPosition);
        Point2D curPosition = ball.getPosition();
        Assert.assertEquals(curPosition.getX(), expectedPosition.getX(), 0.01);
        Assert.assertEquals(curPosition.getY(), expectedPosition.getY(), 0.01);
    }

    @Test
    /**
     * Test: Point2D getDirection()
     * Assert that the correct ball direction is returned.
     */
    public void getDirection() throws Exception {
        Point2D expectedDirection = new Point2D(200,100);
        Ball ball = new Ball(new Point2D(0,0), expectedDirection, 10.0f);
        Point2D curDirection = ball.getDirection();
        Assert.assertEquals(curDirection.getX(), expectedDirection.getX(), 0.01);
        Assert.assertEquals(curDirection.getY(), expectedDirection.getY(), 0.01);
    }

    @Test
    /**
     * Test: void setDirection(Point2D direction)
     * Assert that the correct ball direction is set.
     */
    public void setDirection() throws Exception {
        Point2D expectedDirection = new Point2D(500,600);
        Ball ball = new Ball(new Point2D(0,0), new Point2D(0,0), 10.0f);
        ball.setDirection(expectedDirection);
        Point2D curDirection = ball.getDirection();
        Assert.assertEquals(curDirection.getX(), expectedDirection.getX(), 0.01);
        Assert.assertEquals(curDirection.getY(), expectedDirection.getY(), 0.01);
    }

    @Test
    /**
     * Test: Point2D getNextPosition()
     * Assert that the correct ball position is returned.
     */
    public void getNextPosition() throws Exception {
        Point2D direction = new Point2D(10,20);
        Point2D position = new Point2D(100,200);
        Point2D expectedPosition = position.add(direction);
        Ball ball = new Ball(position, direction, 10.0f);
        Point2D nextPosition = ball.getNextPosition();
        Assert.assertEquals(nextPosition.getX(), expectedPosition.getX(), 0.01);
        Assert.assertEquals(nextPosition.getY(), expectedPosition.getY(), 0.01);
    }

    @Test
    /**
     * Test: void moveBall()
     * Assert that the ball is correctly moved to it's new position.
     */
    public void moveBall() throws Exception {
        Point2D position = new Point2D(100,200);
        Point2D direction = new Point2D(10,20);
        Point2D expectedPosition = position.add(direction);
        Ball ball = new Ball(position, direction, 10.0f);
        ball.moveBall();
        Point2D curPosition = ball.getPosition();
        Assert.assertEquals(curPosition.getX(), expectedPosition.getX(), 0.01);
        Assert.assertEquals(curPosition.getY(), expectedPosition.getY(), 0.01);
    }



}