package Tests;

import Model.Collsion.Collision;
import Model.GameObjects.Ball;
import Model.GameObjects.Physics.RectangleBody;
import Model.ViewObjects.Board;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Created by kendu on 2017-04-30.
 */
class CollisionTest {

    private final static float THRESHOLD = 0.0001f;

    private final static float BALL_RADIUS = 10f;
    private final static float BOARD_RADIUS = 1000f;

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

        float recW = rect.getWidth();
        float recH = rect.getHeight();
        float recX = rect.getX();
        float recY = rect.getY();

        //System.out.println(ball.distance(rect));

        // Trace ball along small fixed distance from all sides of the rectangle, no collision expected.
        for (float s = -1f; s <= 1f; s+=0.05f) {
            // Left edge
            ball.setPosition(recX - recW/2f - BALL_RADIUS - 1f, recY + s*recH/2 );
            Assertions.assertFalse( collision.isCollision(ball.getBody(), rect) );

            // Right edge
            ball.setPosition(recX + recW/2f + BALL_RADIUS + 1f, recY + s*recH/2f );
            Assertions.assertFalse( collision.isCollision(ball.getBody(), rect) );

            // Top edge
            ball.setPosition(recX + s*recW/2f, recY - recH/2f - BALL_RADIUS - 1f );
            Assertions.assertFalse( collision.isCollision(ball.getBody(), rect) );

            // Bottom edge
            ball.setPosition(recX + s*recW/2f, recY + recH/2f + BALL_RADIUS + 1f );
            Assertions.assertFalse( collision.isCollision(ball.getBody(), rect) );
        }

        // Test collision where ball tangent or intersect rectagle. Collision expected.
        float dw = recW/2f-BALL_RADIUS;
        float dh = recH/2f-BALL_RADIUS;
        for (float py = recY-dh; py <= recY+dh; py++) {
            for (float px = recX-dw; px <= recX+dw; px++) {
                ball.setPosition(px, py);
                Assertions.assertTrue( collision.isCollision(ball.getBody(), rect) );
            }
        }

        // Test collision where ball tangent rectangle corners. Collision expected.
        float minDistCircle = (float) Math.sqrt(2*BALL_RADIUS/2f*BALL_RADIUS/2f);
        ball.setPosition(recX-recW/2f-minDistCircle, recY-recH/2f-minDistCircle);     // Top left corner
        Assertions.assertTrue( collision.isCollision(ball.getBody(), rect) );
        ball.setPosition(recX+recW/2f+minDistCircle, recY-recH/2f-minDistCircle);     // Top right corner
        Assertions.assertTrue( collision.isCollision(ball.getBody(), rect) );
        ball.setPosition(recX-recW/2f-minDistCircle, recY+recH/2f-minDistCircle);     // Bottom left corner
        Assertions.assertTrue( collision.isCollision(ball.getBody(), rect) );
        ball.setPosition(recX+recW/2f+minDistCircle, recY+recH/2f-minDistCircle);     // Bottom right corner
        Assertions.assertTrue( collision.isCollision(ball.getBody(), rect) );

        // Test collision where ball almost tangent rectangle corners. No collision expected.
        minDistCircle += 0.1f;
        ball.setPosition(recX-recW/2f-minDistCircle, recY-recH/2f-minDistCircle);     // Top left corner
        Assertions.assertFalse( collision.isCollision(ball.getBody(), rect) );
        ball.setPosition(recX+recW/2f+minDistCircle, recY-recH/2f-minDistCircle);     // Top right corner
        Assertions.assertFalse( collision.isCollision(ball.getBody(), rect) );
        ball.setPosition(recX-recW/2f-minDistCircle, recY+recH/2f+minDistCircle);     // Bottom left corner
        Assertions.assertFalse( collision.isCollision(ball.getBody(), rect) );
        ball.setPosition(recX+recW/2f+minDistCircle, recY+recH/2f+minDistCircle);     // Bottom right corner
        Assertions.assertFalse( collision.isCollision(ball.getBody(), rect) );

    }

    @Test
    void isBodyOutsideRange() {
        ball = new Ball(0f, 0f, BALL_RADIUS, 0f, 0f);
        collision = new Collision();
        float range = BOARD_RADIUS + 100f;
        for (float py = -range; py < range; py+= 50f) {
            for (float px = -range; px < range; px+= 50f) {
                ball.setPosition(px, py);
                boolean actualResult = collision.isOutsideBoardRange(ball, BOARD_RADIUS);
                boolean expectedResult = (Math.sqrt(px*px+py*py) - BALL_RADIUS >= BOARD_RADIUS);

                if (expectedResult != actualResult) {
                    System.out.printf("(%.0f, %.0f)   expected=%b   actual=%b\n",
                           px, py, expectedResult, actualResult);
                    //It's ok not to test this if case ;)
                }
                Assertions.assertTrue(expectedResult == actualResult);
            }
        }
    }
}
