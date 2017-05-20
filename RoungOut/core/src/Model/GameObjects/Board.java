package Model.GameObjects;


import Model.Collision.Collision;
import Model.Collision.CollisionObserver;
import Model.GameObjects.Physics.Body;
import Model.GameObjects.Physics.CircleBody;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Adam on 2017-03-29.
 * Updated by Ken on 2017-05-10.
 */
public class Board implements IBoard, IPowerUp {

    private static float DISTANCE_TOLERANCE = 0.01f;

    protected final int WIDTH;
    protected final int HEIGHT;

    private CircleBody board;
    private Ball lastBallSpawned;
    private float nextCollisionTime;


    private float xPos;
    private float yPos;
    private float radius;

    private Set<Ball> balls;
    private Set<Brick> bricks;
    private ArrayList<Player> players;
    private Set<CollisionObserver> observers;

    public Board(int width, int height) {
        this.WIDTH = width;
        this.HEIGHT = height;
        xPos = width / 2;
        yPos = height / 2;
        radius = (float) Math.sqrt(Math.pow((width / 4), 2) + Math.pow((height / 4), 2));
        balls = new HashSet<Ball>();
        bricks = new HashSet<Brick>();
        players = new ArrayList<Player>();
        observers = new HashSet<CollisionObserver>();
        nextCollisionTime = Collision.estimateNextCollision(this);
        createSampleBoard(WIDTH, HEIGHT);
    }

    // TODO Temporary mock data
    public void createSampleBoard(int WIDTH, int HEIGHT) {
        this.addPlayer(new Player(80f, 30f, WIDTH / 2 - 350, HEIGHT / 2, 0));
        this.addPlayer(new Player(80f, 30f, WIDTH / 2 - 450, HEIGHT / 2, 0));
        this.addBrick(new Brick(WIDTH / 2 - 40, HEIGHT / 2, 30, 30));
        this.addBrick(new Brick(WIDTH / 2, HEIGHT / 2, 30, 30));
        this.addBrick(new SDownBrick(WIDTH / 2 + 40, HEIGHT / 2, 30, 30));
        this.addBrick(new Brick(WIDTH / 2 - 40, HEIGHT / 2 - 40, 30, 30));
        this.addBrick(new Brick(WIDTH / 2, HEIGHT / 2 - 40, 30, 30));
        this.addBrick(new Brick(WIDTH / 2 + 40, HEIGHT / 2 - 40, 30, 30));
        this.addBrick(new SUpBrick(WIDTH / 2 - 40, HEIGHT / 2 + 40, 30, 30));
        this.addBrick(new Brick(WIDTH / 2, HEIGHT / 2 + 40, 30, 30));
        this.addBrick(new Brick(WIDTH / 2 + 40, HEIGHT / 2 + 40, 30, 30));
        this.addBall(new Ball(WIDTH / 2 - 250, HEIGHT / 2 + 20, 30f, (float)Math.PI, 1));
    }

    private float correctAngle(float radians) {
        while (radians < 0) radians += Math.PI * 2f;
        return radians % (float)(Math.PI * 2f);
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
        return bricks;
    }

    public ArrayList<Player> getPlayers() {
        return players;
    }

    public void update(float deltaTime) {
        while (deltaTime > 0) {
            float minTime = Math.min( deltaTime, nextCollisionTime);
            for (Ball ball : balls) {
                ball.move(minTime);
            }
            deltaTime -= minTime;
            if (deltaTime > 0) {
                handleCollisions();
                nextCollisionTime = Collision.estimateNextCollision(this);
                if (nextCollisionTime < 0.001f) {
                    throw new UnknownError("Estimating collision time is faulty.");
                }
            }
        }
    }

    public void addBall(Ball ball) {
        if (ball != null) {
            this.balls.add(ball);
            lastBallSpawned = new Ball(ball);
            nextCollisionTime = Collision.estimateNextCollision(this);
            if (nextCollisionTime < DISTANCE_TOLERANCE) {
                throw new IllegalArgumentException(String.format(
                        "Placing ball at (%.3f, %.3f) cause an immediate collision.",
                        ball.getX(), ball.getY()) );
            }
        }
    }

    public void addBrick(Brick brick) {
        if (brick != null) {
            this.bricks.add(brick);
            nextCollisionTime = Collision.estimateNextCollision(this);
            if (nextCollisionTime < 0.0001f) {
                throw new IllegalArgumentException(String.format(
                        "Placing brick at (%.3f, %.3f) cause an immediate collision.",
                        brick.getX(), brick.getY()) );
            }
        }
    }

    public void addPlayer(Player player) {
        if (player != null) {
            this.players.add(player);
            nextCollisionTime = Collision.estimateNextCollision(this);
            if (nextCollisionTime < 0.0001f) {
                throw new IllegalArgumentException(String.format(
                        "Placing player at (%.3f, %.3f) cause an immediate collision.",
                        player.getPad().getPadXPos(), player.getPad().getPadYPos()) );
            }
        }
    }

    public void removeBall(Ball ball) {
        if (ball != null) {
            this.balls.remove(ball);
            nextCollisionTime = Collision.estimateNextCollision(this);
        }
    }

    public void removeBrick(Brick brick) {
        if (brick != null) {
            this.bricks.remove(brick);
            nextCollisionTime = Collision.estimateNextCollision(this);
        }
    }

    public void removePad(Pad pad) {
        if (pad != null) {
            this.players.remove(pad);
            nextCollisionTime = Collision.estimateNextCollision(this);
        }
    }

    public float distance(Body body) {
        return board.distance(body);
    }

    public void handleCollisions() {
        for (Ball ball : balls) {
            if (Collision.isBallOutsideBoard(this, ball)) {
                notifyBallExitBoard(ball);
                respawnBall(ball);
            }
            for (Brick brick : bricks) {
                if ( ball.distance(brick.getBody()) < DISTANCE_TOLERANCE) {
                    notifyBallHitBrick(ball, brick);
                    bounceBall(ball, Collision.getRectDeflectionAngle(
                            ball.getX(), ball.getY(), brick.getX(), brick.getY(), brick.getWidth(), brick.getHeight()));
                }
            }
            for (Player player : players) {
                if ( ball.distance(player.getPad().getBody()) < DISTANCE_TOLERANCE) {
                    notifyBallHitPlayerPad(ball, player);
                    Pad pad = player.getPad();
                    bounceBall(ball, Collision.getRectDeflectionAngle(
                            ball.getX(), ball.getY(), pad.getPadXPos(), pad.getPadYPos(), pad.getWidth(), pad.getLength()));
                }
            }
            for (Ball otherBall : balls) {
                if (!ball.equals(otherBall) && ball.distance(otherBall.getBody()) < DISTANCE_TOLERANCE) {
                    notifyBallHitBall(ball, otherBall);
                    bounceBall(ball, Collision.getCircleDeflectionAngle(ball.getX(), ball.getY(), otherBall.getX(), otherBall.getY()));
                }
            }
        }
    }

    public void respawnBall(Ball oldBall) {
        Ball newBall = new Ball(lastBallSpawned);
        newBall.setAngle(lastBallSpawned.getAngle() + (float)Math.random());
        balls.remove(oldBall);
        addBall(newBall);
    }

    public void bounceBall(Ball ball, float deflectionAngle) {
        // Move ball out of collision range before bounce in reverse direction.
        while (Collision.estimateTimeSingleBallCollision(this, ball) < 0.01f) {
            ball.move(-0.01f);
        }

        // Check if ball and deflection angle are less than 180.
        deflectionAngle = correctAngle(deflectionAngle);
        float aDiff = deflectionAngle - ball.getAngle();
        if (aDiff > 0 || aDiff < -Math.PI) {
            // If ball is deflected, set a new angle.
            ball.setAngle( ball.getAngle() + 2f * aDiff );
        }
    }

    public void notifyBallExitBoard(Ball ball) {
        for (CollisionObserver observer : observers) {
            observer.onBallExitBoard(ball);
        }
    }

    public void notifyBallHitBrick(Ball ball, Brick brick) {
        for (CollisionObserver observer : observers) {
            observer.onBallHitBrick(ball, brick);
        }
    }

    public void notifyBallHitPlayerPad(Ball ball, Player player) {
        for (CollisionObserver observer : observers) {
            observer.onBallHitPlayerPad(ball, player);
        }
    }

    public void notifyBallHitBall(Ball ball, Ball otherBall) {
        for (CollisionObserver observer : observers) {
            observer.onBallHitBall(ball, otherBall);
        }
    }

    public void addCollisionObserver(CollisionObserver observer) {
        if (observer != null) {
            observers.add(observer);
        }
    }

    public void removeCollisionObserver(CollisionObserver observer) {
        if (observer != null) {
            observers.remove(observer);
        }
    }

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
