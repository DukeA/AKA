package Tests;

import Model.Collision.Collision;
import Model.GameObjects.Ball;
import Model.GameObjects.Board;
import Model.GameObjects.Brick;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Created by kendu on 2017-04-30.
 */
class CollisionTest {

    //
    private static final int BOARD_SIZE = 1000;
    private static final float THRESHOLD = 0.0001f;

    private static final float BALL1_XPOS =   -50f + BOARD_SIZE/2;
    private static final float BALL1_YPOS =     5f + BOARD_SIZE/2;
    private static final float BALL1_RADIUS =  10f;
    private static final float BALL1_ANGLE =    0f; // Right
    private static final float BALL1_SPEED =    1f;

    private static final float BALL2_XPOS =     0f + BOARD_SIZE/2;
    private static final float BALL2_YPOS =    10f + BOARD_SIZE/2;
    private static final float BALL2_RADIUS =  20f;
    private static final float BALL2_ANGLE = (float)Math.PI;    // Left
    private static final float BALL2_SPEED = 5f;

    private Collision collision = new Collision();

    private Board board;
    private Ball ball;
    private Ball ball2;
    private Brick brick;

    @BeforeEach
    public void beforeEach() {
        board = new Board(BOARD_SIZE, BOARD_SIZE);
        ball =  new Ball(BALL1_XPOS, BALL1_YPOS, BALL1_RADIUS, BALL1_ANGLE, BALL1_SPEED);
        ball2 = new Ball(BALL2_XPOS, BALL2_YPOS, BALL2_RADIUS, BALL2_ANGLE, BALL2_SPEED);
    }

    @Test
    public void estimateNoBrickCollisions_0_270deg() {
        // Place a brick just barely right and below the ball, around 271-359 degrees.
        brick = new Brick(BALL1_XPOS + 100f, BALL1_YPOS - 100f, 30f, 30f );
        ball = new Ball( BALL1_XPOS, BALL1_YPOS, BALL1_RADIUS, BALL2_ANGLE, BALL2_SPEED );

        // Expect no collisions from 0..270 degrees.
        int collisions = 0;
        for (int a = 0; a < 270; a++) {
            ball.setAngle( (float)Math.toRadians(a) );
            if ( !Double.isNaN(collision.estimateBrickCollision(ball, brick)) ) {
                collisions++;
            }
        }
        Assertions.assertTrue( collisions == 0 );
    }

    @Test
    public void estimateBrickCollisions_271_359deg() {
        // Place a brick just barely right and below the ball, around 271-359 degrees.
        brick = new Brick(BALL1_XPOS + 100f, BALL1_YPOS - 100f, 30f, 30f );
        ball = new Ball( BALL1_XPOS, BALL1_YPOS, BALL1_RADIUS, BALL2_ANGLE, BALL2_SPEED );

        // Expect collisions 271..359 degrees.
        int collisions = 0;
        for (int a = 271; a < 359; a++) {
            ball.setAngle( (float)Math.toRadians(a) );
            if ( !Double.isNaN(collision.estimateBrickCollision(ball, brick)) ) {
                collisions++;
            }
        }
        Assertions.assertTrue( collisions > 0 );
    }

    @Test
    public void estimateCorrectBrickCollision() {
        // Place a brick just barely right and below the ball, around 271-359 degrees.
        brick = new Brick(BALL1_XPOS + 100f, BALL1_YPOS - 100f, 30f, 30f );
        ball = new Ball( BALL1_XPOS, BALL1_YPOS, BALL1_RADIUS, BALL2_ANGLE, BALL2_SPEED );

        boolean atleastOneCollisionFound = false;
        for (int a = 271; a < 359; a++) {
            ball.setAngle( (float)Math.toRadians(a) );
            ball.setPosition( BALL1_XPOS, BALL1_YPOS );     // Reset position each iteration.

            double timeUntilCollision = collision.estimateBrickCollision(ball, brick);
            if ( !Double.isNaN(timeUntilCollision) ) {
                atleastOneCollisionFound = true;

                // Found a collision angle. Expect no collision before move.
                Assertions.assertTrue( ball.distance(brick.getBody()) > 0);

                // Move ball close to brick, expect no collision.
                ball.move((float)timeUntilCollision - 1f);
                Assertions.assertTrue( ball.distance(brick.getBody()) > 0);

                // Move ball close enough for a collision to occur.
                ball.setPosition( BALL1_XPOS, BALL1_YPOS );     // Reset position each iteration.
                ball.move((float)timeUntilCollision);
                Assertions.assertTrue( ball.distance(brick.getBody()) < 0.01f);
            }

        }

        // Expect at least one collision course found during the test.
        Assertions.assertTrue( atleastOneCollisionFound );

    }

    @Test
    public void checkBoardCollision() {
        // Test all angles from (0..2*PI)
        ball.setSpeed(10f);
        for (float a = 0; a < 2f*Math.PI; a+=0.1f) {
            // Reset position and set angle at each iteration.
            ball.setPosition(BALL1_XPOS, BALL1_YPOS);
            ball.setAngle(a);

            // Estimate time until ball exit board.
            float time = collision.estimateBallGone(board, ball);

            // Make sure ball inside board before move.
            Assertions.assertTrue( time > 0);
            Assertions.assertFalse( collision.isBallOutsideBoard(board, ball) );

            // Move ball very close to the board edge and check if still inside.
            ball.move(time - 1f);
            Assertions.assertFalse( collision.isBallOutsideBoard(board, ball) );

            // Move ball slightly and see see if ball now outside board.
            ball.move(1f);
            Assertions.assertTrue( collision.isBallOutsideBoard(board, ball) );

        }

    }



}