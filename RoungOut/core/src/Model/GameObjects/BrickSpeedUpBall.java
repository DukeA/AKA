package Model.GameObjects;

import Model.Collision.CollisionObserver;

import java.awt.*;

/**
 * An special brick that will speed up the ball hitting it.
 *
 * Created by DukeA on 2017-05-12.
 * Updated by Ken Bäcklund.
 */

public class BrickSpeedUpBall extends Brick implements CollisionObserver {

    // Constructor ////////////////////////////////////////////////////////////

    /**
     * Creates a new Special Brick which speeds up ball.
     * @param xPos the X position where to place brick.
     * @param yPos the Y position where to place brick.
     * @param width the width of the brick.
     * @param height the height of the brick.
     */
    public BrickSpeedUpBall(float xPos, float yPos, float width, float height) {
        super(xPos, yPos, width, height);
        this.brickvalue = BrickType.SPEED_UP_BALL;
    }

    /**
     * Listen to when balls hits a brick. If this brick then speed up ball.
     * @param ball the ball that hit the brick.
     * @param brick the brick that got hit.
     */
    public void onBallHitBrick(Ball ball, Brick brick) {
        // Check if ball hit this brick
        if (brick.equals(this)) {
            // Speed up the ball by +50%.
            ball.setSpeed(ball.getSpeed() * 1.50f);
        }
    }

    // Unused listener events /////////////////////////////////////////////////

    // This brick effect does not affect balls moving outside board.
    public void onBallExitBoard(Ball lostBall) {}
    // This brick effect does not affect balls hitting other balls.
    public void onBallHitBall(Ball ball, Ball otherBall) {}
    // This brick effect does not affect when player pad is hit.
    public void onBallHitPlayerPad(Ball ball, Player player) {}

}
