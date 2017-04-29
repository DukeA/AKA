package MVCPrototype;

import Objects.Ball;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by kendu on 2017-04-27.
 */
public class GameBoard {

    private Set<Ball> balls;
    private double boardRadius;

    public GameBoard(double boardRadius) {
        balls = new HashSet<Ball>();
    }

    public void addBall(Ball ball) {
        balls.add(ball);
    }

    public void removeBall(Ball ball) {
        balls.remove(ball);
    }

    public void updateBoard() {
        // TODO: Move balls, detect collisions
    }

    public double getBoardRadius() {
        return boardRadius;
    }

    public void setBoardRadius(double radius) {
        this.boardRadius = radius;
    }
}
