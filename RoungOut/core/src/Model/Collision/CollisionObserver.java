package Model.Collision;

import Model.GameObjects.Ball;
import Model.GameObjects.Brick;
import Model.GameObjects.Player;

/**
 * Interface for observing board collisions
 *
 * @author Ken Bäcklund
 */
public interface CollisionObserver {

    /**
     * Observer method listening to when a ball exits the board.
     * @param lostBall the ball that got lost.
     */
    void onBallExitBoard(Ball lostBall);

    /**
     * Observer method for listening to when a ball collides with another ball.
     * @param ball the ball that got hit.
     * @param otherBall the other ball that got hit.
     */
    void onBallHitBall(Ball ball, Ball otherBall);

    /**
     * Observer method for listening to when a ball hits a brick.
     * @param ball the ball that hit the brick.
     * @param brick the brick that got hit.
     */
    void onBallHitBrick(Ball ball, Brick brick);

    /**
     * Observer method for listening to when a ball hits a player (and it's related pad).
     * @param ball the ball that hit the player pad.
     * @param player the player and it's related pad that got hit.
     */
    void onBallHitPlayerPad(Ball ball, Player player);
}
