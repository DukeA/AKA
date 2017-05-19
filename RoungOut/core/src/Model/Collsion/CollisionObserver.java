package Model.Collsion;

import Model.GameObjects.Ball;
import Model.GameObjects.Brick;
import Model.GameObjects.Player;

/**
 * Interface for all things collidable
 *
 * @author Ken Bäcklund
 */
public interface CollisionObserver {
    void onBallExitBoard(Ball lostBall);
    void onBallHitBall(Ball ball, Ball otherBall);
    void onBallHitBrick(Ball ball, Brick brick);
    void onBallHitPlayerPad(Ball ball, Player player);
}
