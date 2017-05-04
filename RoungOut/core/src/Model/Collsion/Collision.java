package Model.Collsion;

import Model.GameObjects.Ball;
import Model.GameObjects.Physics.Body;
import Model.GameObjects.Physics.CircleBody;
import Model.GameObjects.Board;

/**
 * @author Ken Bäcklund
 */
public class Collision {

    private CircleBody board;       // TODO:  Temporary solution, will use proper Board class later.

    public Collision() {
        board = new CircleBody(0f, 0f, 1000f);  // TODO: Use proper board class
    }

    public boolean isCollision(Body someBody, Body anyBody) {
        return (someBody.distance(anyBody) <= 0.0001f);
    }

    public boolean isOutsideBoardRange(Ball ball, double boardRadius) {
        board.setRadius(boardRadius);   // TODO: Temporary solution
        return (ball.distance(board) >= 0.0001f);
    }

}
