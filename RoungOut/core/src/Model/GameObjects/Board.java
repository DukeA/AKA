package Model.GameObjects;


import Model.Collision.Collision;
import Model.Collision.CollisionObserver;
import Model.GameObjects.Physics.Body;
import Model.GameObjects.Physics.CircleBody;

import java.util.*;

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
    private Collision collision = new Collision();

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
        radius = height/2+100;
        balls = new HashSet<Ball>();
        bricks = new HashSet<Brick>();
        players = new ArrayList<Player>();
        observers = new HashSet<CollisionObserver>();
        createSampleBoard(WIDTH, HEIGHT);
        nextCollisionTime = 0;
    }

    // TODO Temporary mock data
    public void createSampleBoard(int WIDTH, int HEIGHT) {
        float padLength =80f;
        float padWidth =30f;
        float brickWidth =30f;
        float brickHeight =30f;
        this.addPlayer(new Player(padLength, padWidth, WIDTH/2,HEIGHT/2,
                WIDTH / 2 - 350, HEIGHT / 2, 1));
        this.addPlayer(new Player(padLength, padWidth,WIDTH/2,HEIGHT/2,
                WIDTH / 2 - 450, HEIGHT / 2, 1));

        this.addBrick(new Brick(WIDTH / 2 - 40-brickWidth/2, HEIGHT / 2-brickHeight/2
                , brickWidth, brickHeight));
        this.addBrick(new Brick(WIDTH / 2 - 40-brickWidth/2, HEIGHT / 2-brickHeight/2
                , brickWidth, brickHeight));
        this.addBrick(new Brick(WIDTH / 2-brickWidth/2, HEIGHT / 2-brickHeight/2
                , brickWidth, brickHeight));
        this.addBrick(new SDownBrick(WIDTH / 2 + 40-brickWidth/2, HEIGHT / 2-brickHeight/2
                , brickWidth, brickHeight));
        this.addBrick(new Brick(WIDTH / 2 - 40-brickWidth/2, HEIGHT / 2 - 40-brickHeight/2
                , brickWidth, brickHeight));
        this.addBrick(new Brick(WIDTH / 2-brickWidth/2, HEIGHT / 2 - 40-brickHeight/2
                , brickWidth, brickHeight));
        this.addBrick(new Brick(WIDTH / 2 + 40-brickWidth/2, HEIGHT / 2 - 40-brickHeight/2
                , brickWidth, brickHeight));
        this.addBrick(new SUpBrick(WIDTH / 2 - 40-brickWidth/2, HEIGHT / 2 + 40-brickHeight/2
                , brickWidth, brickHeight));
        this.addBrick(new Brick(WIDTH / 2-brickWidth/2, HEIGHT / 2 + 40-brickHeight/2
                , brickWidth, brickHeight));
        this.addBrick(new Brick(WIDTH / 2 + 40-brickWidth/2, HEIGHT / 2 + 40-brickHeight/2
                , brickWidth, brickHeight));

        this.addBall(new Ball(WIDTH / 2 - 250, HEIGHT / 2 + 20, 30f, 0.05f, 100));
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
        return balls;
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
            nextCollisionTime -= minTime;
            if (deltaTime > 0) {
                handleCollisions();
                nextCollisionTime = collision.estimateNextCollision(this);
            }
        }
    }

    public void addBall(Ball ball) {
        if (ball != null) {
            this.balls.add(ball);
            lastBallSpawned = new Ball(ball);
            nextCollisionTime = collision.estimateNextCollision(this);
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
            nextCollisionTime = collision.estimateNextCollision(this);
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
            nextCollisionTime = collision.estimateNextCollision(this);
            if (nextCollisionTime < 0.0001f) {
                throw new IllegalArgumentException(String.format(
                        "Placing player at (%.3f, %.3f) cause an immediate collision.",
                        player.getPad().getPadXPos(), player.getPad().getPadYPos()) );
            }
        }
    }

    /*
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
    */

    public float distance(Body body) {
        return board.distance(body);
    }

    public void handleCollisions() {
        for (Ball ball : balls) {
            if (collision.isBallOutsideBoard(this, ball)) {
                notifyBallExitBoard(ball);
                respawnBall(ball);
            }
            for (Brick brick : bricks) {
                if (brick.isDestroyed()) {
                    continue;
                }
                if ( ball.distance(brick.getBody()) < DISTANCE_TOLERANCE) {
                    notifyBallHitBrick(ball, brick);
                    untangleBall(ball);
                    bounceBall(ball, collision.getRectDeflectionAngle(
                            ball.getX(), ball.getY(), brick.getX(), brick.getY(), brick.getWidth(), brick.getHeight()));
                    brick.markDestroyed();
                }
            }
            for (Player player : players) {
                if ( ball.distance(player.getPad().getBody()) < DISTANCE_TOLERANCE) {
                    notifyBallHitPlayerPad(ball, player);
                    untangleBall(ball);
                    Pad pad = player.getPad();
                    bounceBall(ball, collision.getRectDeflectionAngle(
                            ball.getX(), ball.getY(), pad.getPadXPos(), pad.getPadYPos(), pad.getWidth(), pad.getLength()));
                }
            }
            for (Ball otherBall : balls) {
                if (!ball.equals(otherBall) && ball.distance(otherBall.getBody()) < DISTANCE_TOLERANCE) {
                    notifyBallHitBall(ball, otherBall);
                    untangleBall(ball);
                    bounceBall(ball, collision.getCircleDeflectionAngle(ball.getX(), ball.getY(), otherBall.getX(), otherBall.getY()));
                }
            }
            ball.move(0.01f);
        }
    }

    public void respawnBall(Ball oldBall) {
        Ball newBall = new Ball(lastBallSpawned);
        newBall.setAngle(lastBallSpawned.getAngle() + (float)Math.random());
        balls.remove(oldBall);
        addBall(newBall);
    }

    // Untangle ball after a collision occurred.
    public void untangleBall(Ball ball) {
        // Move ball out of collision range before bounce in reverse direction.
        //System.out.printf("Adjusting position before bounce. Ball at x%.4f y%.4f\n", ball.getX(), ball.getY());
        ball.move(-0.05f);
    }

    public void bounceBall(Ball ball, float deflectionAngle) {
        // Check if ball and deflection angle are less than 180.
        deflectionAngle = correctAngle(deflectionAngle);
        float aDiff = deflectionAngle - ball.getAngle();
        float a1 = (float)Math.toDegrees(ball.getAngle());
        float a2 = (float)Math.toDegrees(  (ball.getAngle() + 2f * aDiff) % (2f*Math.PI) );
        if (aDiff > 0 || aDiff < -Math.PI) {
            // If ball is deflected, set a new angle.
            ball.setAngle( ball.getAngle() + 2f * aDiff );
            //System.out.printf("Ball deflected from %.3f to %.3f degrees\n", a1, a2);
        }
        else {
            //System.out.printf("Ball at %.3f degrees, not deflected (deflection angle = %.3f)\n", a1, Math.toDegrees(deflectionAngle));
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
