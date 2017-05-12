package Model.GameObjects;


import java.util.HashSet;
import java.util.Set;

/**
 * Created by Adam on 2017-03-29.
 * Updated by Ken on 2017-05-10.
 */
public class Board implements IBoard {


    private float xPos;
    private float yPos;
    private float radius;

    private Set<Ball> balls;
    private Set<Brick> bricks;
    private Set<Player> players;

    public Board(int Width, int Height) {
        xPos = Width/2;
        yPos = Height/2;
        radius = (float) Math.sqrt(Math.pow((Width/4),2)+Math.pow((Height/4),2));
        balls = new HashSet<Ball>();
        bricks = new HashSet<Brick>();
        players = new HashSet<Player>();
    }

    public float getXPos() {
        return this.xPos;
    }
    public float getYPos() {
        return this.yPos;
    }
    public float getRadius() {
        return this.radius;
    }

    public Set<Ball> getBalls() {
        return this.balls;
    }

    public Set<Brick> getBricks() {
        return this.bricks;
    }

    public Set<Player> getPlayers() {
        return this.players;
    }

    public void update(float deltaTime) {
        for (Ball ball : balls) {
            ball.move(deltaTime);

        }
    }

    public void addBall(Ball ball) {
        if (ball != null) {
            this.balls.add(ball);
        }
    }

    public void addBrick(Brick brick) {
        if (brick != null) {
            this.bricks.add(brick);
        }
    }

    public void addPlayer(Player pad) {
        if (pad != null) {
            this.players.add(pad);
        }
    }

    public void removeBall(Ball ball) {
        if (ball != null) {
            this.balls.remove(ball);
        }
    }

    public void removeBrick(Brick brick) {
        if (brick != null) {
            this.bricks.remove(brick);
        }
    }

    public void removePad(Pad pad) {
        if (pad != null) {
            this.players.remove(pad);
        }
    }


}
