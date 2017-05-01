package Tests;

import Model.Collsion.Collision;
import Model.GameObjects.Ball;
import Model.GameObjects.Physics.RectangleBody;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Created by kendu on 2017-04-30.
 */
class CollisionTest {

    private final static double THRESHOLD = 0.0001f;

    private final static double BALL_RADIUS = 10f;

    private Collision collision;
    private RectangleBody rect;
    private Ball ball;

    @Test
    void isCollisionBallRectangle() {
        collision = new Collision();
        ball = new Ball(60f, 100f, 10f, 0f, 1f);
        rect = new RectangleBody(100f,100f,40f,20f);
        double expectedDistance1 = 1f;
        double expectedDistance0 = 0f;

        double recW = rect.getWidth();
        double recH = rect.getHeight();
        double recX = rect.getX();
        double recY = rect.getY();

        //System.out.println(ball.distance(rect));

        // Trace ball along small fixed distance from all sides of the rectangle, no collision expected.
        for (double s = -1f; s <= 1f; s+=0.05f) {
            // Left edge
            ball.setPosition(recX - recW/2f - BALL_RADIUS - 1f, recY + s*recH/2f );
            Assertions.assertFalse( collision.isCollision(ball, rect) );

            // Right edge
            ball.setPosition(recX + recW/2f + BALL_RADIUS + 1f, recY + s*recH/2f );
            Assertions.assertFalse( collision.isCollision(ball, rect) );

            // Top edge
            ball.setPosition(recX + s*recW/2f, recY - recH/2f - BALL_RADIUS - 1f );
            Assertions.assertFalse( collision.isCollision(ball, rect) );

            // Bottom edge
            ball.setPosition(recX + s*recW/2f, recY + recH/2f + BALL_RADIUS + 1f );
            Assertions.assertFalse( collision.isCollision(ball, rect) );
        }

        // Test collision where ball tangent or intersect rectagle. Collision expected.
        double dw = recW/2f-BALL_RADIUS;
        double dh = recH/2f-BALL_RADIUS;
        for (double py = recY-dh; py <= recY+dh; py++) {
            for (double px = recX-dw; px <= recX+dw; px++) {
                ball.setPosition(px, py);
                Assertions.assertTrue( collision.isCollision(ball, rect) );
            }
        }

        // Test collision where ball tangent rectangle corners. Collision expected.
        double minDistCircle = Math.sqrt(2*BALL_RADIUS/2f*BALL_RADIUS/2f);
        ball.setPosition(recX-recW/2f-minDistCircle, recY-recH/2f-minDistCircle);     // Top left corner
        Assertions.assertTrue( collision.isCollision(ball, rect) );
        ball.setPosition(recX+recW/2f+minDistCircle, recY-recH/2f-minDistCircle);     // Top right corner
        Assertions.assertTrue( collision.isCollision(ball, rect) );
        ball.setPosition(recX-recW/2f-minDistCircle, recY+recH/2f-minDistCircle);     // Bottom left corner
        Assertions.assertTrue( collision.isCollision(ball, rect) );
        ball.setPosition(recX+recW/2f+minDistCircle, recY+recH/2f-minDistCircle);     // Bottom right corner
        Assertions.assertTrue( collision.isCollision(ball, rect) );

        // Test collision where ball almost tangent rectangle corners. No collision expected.
        minDistCircle += 0.1f;
        ball.setPosition(recX-recW/2f-minDistCircle, recY-recH/2f-minDistCircle);     // Top left corner
        Assertions.assertFalse( collision.isCollision(ball, rect) );
        ball.setPosition(recX+recW/2f+minDistCircle, recY-recH/2f-minDistCircle);     // Top right corner
        Assertions.assertFalse( collision.isCollision(ball, rect) );
        ball.setPosition(recX-recW/2f-minDistCircle, recY+recH/2f+minDistCircle);     // Bottom left corner
        Assertions.assertFalse( collision.isCollision(ball, rect) );
        ball.setPosition(recX+recW/2f+minDistCircle, recY+recH/2f+minDistCircle);     // Bottom right corner
        Assertions.assertFalse( collision.isCollision(ball, rect) );

    }

}
