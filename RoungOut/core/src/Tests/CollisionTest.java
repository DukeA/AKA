package Tests;

import Model.Collsion.Collision;
import Model.GameObjects.Ball;
import Model.GameObjects.Board;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Created by kendu on 2017-04-30.
 */
class CollisionTest {

    //
    private static final int BOARD_SIZE = 990;     // Radius ~350, a nice and round number.

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

    private Board board;
    private Ball ball;
    private Ball ball2;

    @BeforeEach
    public void beforeEach() {
        board = new Board(BOARD_SIZE, BOARD_SIZE);
        ball =  new Ball(BALL1_XPOS, BALL1_YPOS, BALL1_RADIUS, BALL1_ANGLE, BALL1_SPEED);
        ball2 = new Ball(BALL2_XPOS, BALL2_YPOS, BALL2_RADIUS, BALL2_ANGLE, BALL2_SPEED);
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
            float time = Collision.estimateBallGone(board, ball);

            // Make sure ball inside board before move.
            Assertions.assertTrue( time > 0);
            Assertions.assertFalse( Collision.isBallOutsideBoard(board, ball) );

            // Move ball very close to the board edge and check if still inside.
            ball.move(time - 1f);
            Assertions.assertFalse( Collision.isBallOutsideBoard(board, ball) );

            // Move ball slightly and see see if ball now outside board.
            ball.move(1f);
            Assertions.assertTrue( Collision.isBallOutsideBoard(board, ball) );

        }

    }



}