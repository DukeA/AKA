package Tests;

import Model.Collision.Collision;
import Model.GameObjects.Ball;
import Model.GameObjects.Board;
import Model.GameObjects.Brick;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Created by kendu on 2017-04-30.
 */
class CollisionTest {

    private static final int BOARD_SIZE = 1000;
    private static final double THRESHOLD = 0.0001f;

    private static final float BALL1_XPOS =   -50f + BOARD_SIZE/2;
    private static final float BALL1_YPOS =     5f + BOARD_SIZE/2;
    private static final float BALL1_RADIUS =  10f;
    private static final float BALL1_ANGLE =    0f; // Right
    private static final float BALL1_SPEED =    3f;

    private static final float BALL2_XPOS =     0f + BOARD_SIZE/2;
    private static final float BALL2_YPOS =    10f + BOARD_SIZE/2;
    private static final float BALL2_RADIUS =  20f;
    private static final float BALL2_ANGLE = (float)Math.PI;    // Left
    private static final float BALL2_SPEED = 5f;

    private static final float BRICK_XPOS =     0f + BOARD_SIZE/2;
    private static final float BRICK_YPOS =    10f + BOARD_SIZE/2;
    private static final float BRICK_WIDTH =   40f;
    private static final float BRICK_HEIGHT =  10f;

    private Board board;
    private Collision collision;
    private Ball ball;
    private Ball ball2;
    private Brick brick;

    @BeforeEach
    public void beforeEach() {
        board = new Board(BOARD_SIZE, BOARD_SIZE);
        collision = new Collision();
        ball =  new Ball(BALL1_XPOS, BALL1_YPOS, BALL1_RADIUS,
                         BALL1_ANGLE, BALL1_SPEED);
        ball2 = new Ball(BALL2_XPOS, BALL2_YPOS, BALL2_RADIUS,
                         BALL2_ANGLE, BALL2_SPEED);
        brick = new Brick(BRICK_XPOS, BRICK_YPOS,
                BRICK_WIDTH, BRICK_HEIGHT);
    }



    // Test deflection angles /////////////////////////////////////////////////
    @Nested
    @DisplayName("Test deflection angles")
    class DeflectionAngles {

        @Test
        @DisplayName("Test deflection angle between two circles")
        public void getCircleDeflectionAngle() {
            for (double angle=0; angle<Math.PI*2;
                                         angle+= Math.PI/32) {
                // Place a ball at various angles around other ball.
                float px = BALL1_XPOS + 100*(float)Math.cos(angle);
                float py = BALL1_YPOS + 100*(float)Math.sin(angle);
                ball2.setPosition(px, py);

                // Get expected and actual angle.
                double expectedAngle = (angle + Math.PI/2)%(2*Math.PI);
                double actualAngle = collision.getCircleDeflectionAngle(
                        ball2.getX(), ball2.getY(),
                        ball.getX(), ball.getY());

                // Assert if values are similar within given threshold.
                Assertions.assertEquals(expectedAngle, actualAngle, THRESHOLD);
            }

        }

        @Test
        @DisplayName("Test deflection angle between circle and rectangle")
        public void getRectangleDeflectionAngle() {
            for (double angle=0; angle<Math.PI*2;
                                         angle+= Math.PI/32) {
                // Place a ball at various angles around a brick.
                double bX = BRICK_XPOS + 100*(float)Math.cos(angle);
                double bY = BRICK_YPOS + 100*(float)Math.sin(angle);
                ball.setPosition((float)bX, (float)bY);

                // Find closest point on rectangle facing the ball.
                double rX = brick.getX() + Math.min(BRICK_WIDTH/2,
                        Math.max(-BRICK_WIDTH/2, bX - brick.getX()));
                double rY = brick.getY() + Math.min(BRICK_HEIGHT/2,
                        Math.max(-BRICK_HEIGHT/2, bY - brick.getY()));

                // Get expected and actual angle.
                double expectedAngle =
                        (Math.atan2( ball.getY() - rY, ball.getX() - rX)
                        + 3*Math.PI/2)%(2*Math.PI);
                double actualAngle = collision.getRectangleDeflectionAngle(
                        ball.getX(), ball.getY(),
                        brick.getX(), brick.getY(),
                        brick.getWidth(), brick.getHeight() );

                // Assert if values are similar within given threshold.
                Assertions.assertEquals(expectedAngle, actualAngle, THRESHOLD);
            }

        }


    }



    // Test deflection angles /////////////////////////////////////////////////
    @Test
    @DisplayName("Test estimation until ball exists board")
    public void checkBoardCollision() {
        // Distance to test just before and after board edge
        float stepDistance = 0.05f;

        // Test various angles in a full rotation.
        for (double angle=0; angle < 2*Math.PI; angle+=Math.PI/32) {
            // Reset position and set angle at each iteration.
            ball.setPosition(BALL1_XPOS, BALL1_YPOS);
            ball.setDirection((float)angle);

            // Estimate time until ball exit board.
            double time = collision.estimateBallGone(board, ball);

            // Test distance moving almost to the edge. Should be negative.
            ball.move((float)time - stepDistance);
            double expectNegativeDistance =
                    Math.sqrt(Math.pow(ball.getX() - board.getXPos(), 2) +
                              Math.pow(ball.getY() - board.getYPos(), 2))
                        - ball.getRadius() - board.getRadius();
            Assertions.assertTrue(expectNegativeDistance < 0);

            // Test distance moving exactly to the edge. Should be zero.
            ball.move(stepDistance);
            double expectZeroDistance = Math.abs(
                    Math.sqrt(Math.pow(ball.getX() - board.getXPos(), 2) +
                              Math.pow(ball.getY() - board.getYPos(), 2))
                        - ball.getRadius() - board.getRadius());
            Assertions.assertTrue(expectZeroDistance < 0.01f);

            // Test distance moving slighty past edge. Should be positive.
            ball.move(stepDistance);
            double expectPositiveDistance =
                    Math.sqrt(Math.pow(ball.getX() - board.getXPos(), 2) +
                              Math.pow(ball.getY() - board.getYPos(), 2))
                        - ball.getRadius() - board.getRadius();
            Assertions.assertTrue(expectPositiveDistance > 0);

        }

    }



    // Test brick collisions //////////////////////////////////////////////////
    @Nested
    @DisplayName("Test brick collisions")
    class BrickCollisions {

        // Helper method to count brick collisions within a given range.
        private int countBrickCollisions(int fromAngle, int toAngle, int steps) {

            // Place a brick just barely right and below the ball at a set angle.
            // Collisions should only occur between ~271-359 degree range.
            brick = new Brick(BALL1_XPOS + 100f, BALL1_YPOS - 100f, 30f, 30f);
            ball = new Ball(BALL1_XPOS, BALL1_YPOS, BALL1_RADIUS, BALL2_ANGLE, BALL2_SPEED);

            int collisions = 0;
            for (int a = fromAngle; a < toAngle; a += steps) {
                ball.setDirection((float) Math.toRadians(a));
                if (!Double.isNaN(collision.estimateBrickCollision(ball, brick))) {
                    collisions++;
                }
            }
            return collisions;
        }

        @Test
        @DisplayName("Test collisions found at different angles")
        public void findBrickCollisions() {

            int expectNoCollisions = countBrickCollisions(
                    0, 270, 1);
            int expectSomeCollisions = countBrickCollisions(
                    271, 359, 1);
            Assertions.assertTrue(expectNoCollisions == 0);
            Assertions.assertTrue(expectSomeCollisions > 0);
        }

        @Test
        @DisplayName("Test estimated collision distance")
        public void estimateBrickCollisionDistance() {
            // Place a brick just barely right and below the ball, around 271-359 degrees.
            brick = new Brick(BALL1_XPOS + 100f, BALL1_YPOS - 100f, 30f, 30f);
            ball = new Ball(BALL1_XPOS, BALL1_YPOS, BALL1_RADIUS, BALL2_ANGLE, BALL2_SPEED);

            boolean atleastOneCollisionFound = false;
            for (int a = 271; a < 359; a++) {
                // Reset ball angle and position each iteration.
                ball.setDirection((float) Math.toRadians(a));
                ball.setPosition(BALL1_XPOS, BALL1_YPOS);

                // Estimate time until next collision,
                //     ignore NaN results (no estimated collision).
                double timeUntilCollision = collision.estimateBrickCollision(ball, brick);
                if (Double.isNaN(timeUntilCollision))
                    continue;

                atleastOneCollisionFound = true;

                // Move ball close to brick, expect no collision.
                ball.move((float) timeUntilCollision - 0.5f);
                boolean expectPositiveDistance =
                        ball.distance(brick.getBody()) > 0;
                Assertions.assertTrue(expectPositiveDistance);

                // Move ball to estimated collision range, expect collision.
                ball.move(0.5f);
                boolean expectZeroDistance =
                        Math.abs(ball.distance(brick.getBody())) < THRESHOLD;
                Assertions.assertTrue(expectZeroDistance);

            }

            // Expect at least one collision course found during the test.
            Assertions.assertTrue(atleastOneCollisionFound);
        }
    }

    @Test
    @DisplayName("Test collision between moving balls")
    public void movingBallCollisions() {
        // Test moving both balls in various angles

        int collisionCount = 0;
        for (double angle1=0; angle1 < 2*Math.PI; angle1++) {
            for (double angle2=0; angle2 < 2*Math.PI; angle2++) {

                // Reset ball positions each iteration.
                ball.setPosition(BALL1_XPOS, BALL1_YPOS);
                ball2.setPosition(BALL2_XPOS, BALL2_YPOS);

                // Get estimated collision time.
                // Only test when we have an actual estimation, != NaN.
                double time = collision.estimateBallCollision(ball, ball2);
                if (Double.isNaN(time))
                    continue;

                collisionCount++;
                ball.move((float)time);
                ball2.move((float)time);

                // Calculate circle distance.
                double dx = ball.getX() - ball2.getX();
                double dy = ball.getY() - ball2.getY();
                double totRadius = ball.getRadius() + ball2.getRadius();
                double distance = Math.sqrt(dx*dx+dy*dy)-totRadius;

                // Expect distance to be (close enough to) zero.
                Assertions.assertTrue( distance < THRESHOLD);

            }
        }
        // Expect at least one collision being tested above.
        Assertions.assertTrue( collisionCount > 0);
    }

    @Nested
    @DisplayName("Test pad collisions")
    class PadCollisons {

        @Test
        @DisplayName("NOT YET IMPLEMENTED - Estimate pad collision distance")
        public void estimatePadDistance() {

            Assertions.fail("Not yet implemented");

        }

    }
}