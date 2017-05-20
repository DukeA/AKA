package Model.GameObjects;


import java.util.HashSet;
import java.util.Set;

/**
 * Created by Adam on 2017-03-29.
 * Updated by Ken on 2017-05-10.
 */
public class Board implements IBoard, IPowerUp {


    private float xPos;
    private float yPos;
    private float radius;

    private Set<Ball> balls;
    private Set<Brick> bricks;
    private Set<Player> players;

    public Board(int Width, int Height) {
        xPos = Width / 2;
        yPos = Height / 2;
        radius = (float) Math.sqrt(Math.pow((Width / 4), 2) + Math.pow((Height / 4), 2));
        balls = new HashSet<Ball>();
        bricks = new HashSet<Brick>();
        players = new HashSet<Player>();
        createSampleBoard(Width, Height);

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

    public void createSampleBoard(int WIDTH, int HEIGHT) {
        float PadLength =80f;
        float PadWidth =30f;
        float BrickWidth =30f;
        float BrickLength =30f;
        this.addPlayer(new Player(PadLength, PadWidth, WIDTH/2,HEIGHT/2,
                WIDTH / 2 - 350+PadWidth/2, HEIGHT / 2-PadLength/2, 1));
        this.addPlayer(new Player(PadLength, PadWidth,WIDTH/2,HEIGHT/2,
                WIDTH / 2 - 450+PadWidth/2, HEIGHT / 2-PadLength/2, 1));
        this.addBrick(new Brick(WIDTH / 2 - 40+BrickWidth/2, HEIGHT / 2-BrickLength/2
                , BrickWidth, BrickLength));
        this.addBrick(new Brick(WIDTH / 2+BrickWidth/2, HEIGHT / 2-BrickLength/2
                , BrickWidth, BrickLength));
        this.addBrick(new SDownBrick(WIDTH / 2 + 40+BrickWidth/2, HEIGHT / 2-BrickLength/2
                , BrickWidth, BrickLength));
        this.addBrick(new Brick(WIDTH / 2 - 40+BrickWidth/2, HEIGHT / 2 - 40-BrickLength/2
                , BrickWidth, BrickLength));
        this.addBrick(new Brick(WIDTH / 2+BrickWidth/2, HEIGHT / 2 - 40-BrickLength/2
                , BrickWidth, BrickLength));
        this.addBrick(new Brick(WIDTH / 2 + 40+BrickWidth/2, HEIGHT / 2 - 40-BrickLength/2
                , BrickWidth, BrickLength));
        this.addBrick(new SUpBrick(WIDTH / 2 - 40+BrickWidth/2, HEIGHT / 2 + 40-BrickLength/2
                , BrickWidth, BrickLength));
        this.addBrick(new Brick(WIDTH / 2+BrickWidth/2, HEIGHT / 2 + 40-BrickLength/2
                , BrickWidth, BrickLength));
        this.addBrick(new Brick(WIDTH / 2 + 40+BrickWidth/2, HEIGHT / 2 + 40-BrickLength/2
                , BrickWidth, BrickLength));
        this.addBall(new Ball(WIDTH / 2 - 250, HEIGHT / 2 + 20, 30f, 1, 100));
    }


    @Override
    public void pSpeedUP() {
        for (Ball ball : balls) {
            for (Player pad : players) {
                ball.setSpeed(ball.getSpeed()
                        + getPspeedUp());
                pad.getPad().setSpeed(
                        pad.getPad().getPadSpeed()
                                + getPspeedUp());
            }

        }
    }


    @Override
    public void pSpeedDown() {
        for (Ball ball : balls) {
            for (Player pad : players) {
                ball.setSpeed(ball.getSpeed() + getPspeedDown());

                pad.getPad().setSpeed(
                        pad.getPad().getPadSpeed() + getPspeedDown());
            }

        }
    }

    @Override
    public void effectOver() {
        for (Ball ball : balls) {
            for (Player pad : players) {
                ball.setSpeed(ball.getSpeed() - getPspeedUp() + getPspeedDown());
                pad.getPad().setSpeed(
                        pad.getPad().getPadSpeed() - getPspeedUp() + getPspeedDown());
            }
        }
    }

    @Override
    public float getPspeedUp() {
        float brickspeed = 0;
        for (Brick brick : bricks) {
            if (brick.getClass() == SUpBrick.class
                    && brick.equals(null)) {
                brickspeed = brick.getSpeed();
            }
        }
        return brickspeed;
    }

    @Override
    public float getPspeedDown() {
        float brickspeed = 0;
        for (Brick brick : bricks) {
            if (brick.getClass() == SDownBrick.class &&
                    brick.equals(null)) {
                brickspeed = brick.getSpeed();
            }
        }
        return brickspeed;
    }
}
