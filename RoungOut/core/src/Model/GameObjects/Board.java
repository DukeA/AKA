package Model.GameObjects;


import Model.GameObjects.Physics.Body;
import Model.GameObjects.Physics.CircleBody;
import Model.Collsion.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Adam on 2017-03-29.
 * Updated by Ken on 2017-05-10.
 */
public class Board implements IBoard, IPowerUp {

    private static float ADJUST_COLLISION_DISTANCE = 0.1f;

    protected final int WIDTH;
    protected final int HEIGHT;

    private CircleBody board;
    private Ball lastBallModelAdded;
    private float nextCollisionTime;

    private float xPos;
    private float yPos;
    private float radius;

    private Set<Ball> balls;
    private Set<Brick> bricks;
    private Set<Player> players;
    private Set<CollisionObserver> observers;

    private static final float PIx2 = (float)Math.PI*2f;

    public Board(int width, int height) {
        this.WIDTH = width;
        this.HEIGHT = height;

        xPos = width / 2;
        yPos = height / 2;
        radius = (float) Math.sqrt(Math.pow((width / 4), 2) + Math.pow((height / 4), 2));
        balls = new HashSet<Ball>();
        bricks = new HashSet<Brick>();
        players = new HashSet<Player>();

        observers = new HashSet<CollisionObserver>();
        nextCollisionTime = Collision.estimateNextCollision(this);

        createSampleBoard(width, height);

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
        while (deltaTime > 0) {
            float minTime = Math.min( deltaTime, nextCollisionTime);
            for (Ball ball : balls) {
                ball.move(minTime);
            }
            deltaTime -= minTime;
            if (deltaTime > 0) {
                handleCollisions();
                adjustBallsAfterCollision(ADJUST_COLLISION_DISTANCE);
                nextCollisionTime = Collision.estimateNextCollision(this);
                if (nextCollisionTime < 0.001f) {
                    throw new UnknownError("Estimating collision time is faulty.");
                }
            }
        }
    }

    public void adjustBallsAfterCollision(float adjustmentDistance) {
        System.out.print("AdjustCollisions: ");
        for (Ball ball : balls) {
            while (Collision.estimateTimeSingleBallCollision(this, ball) < adjustmentDistance ) {
                System.out.printf("From (x=%.2f, y=%.2f) ", ball.getX(), ball.getY());
                ball.move(-adjustmentDistance);
                System.out.printf(" to (x=%.2f, y=%.2f).   ", ball.getX(), ball.getY());
            }
        }
        System.out.println();
    }

    public void addBall(Ball ball) {
        if (ball != null) {
            this.balls.add(ball);
            lastBallModelAdded = new Ball(ball);
            nextCollisionTime = Collision.estimateNextCollision(this);
            if (nextCollisionTime < ADJUST_COLLISION_DISTANCE) {
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
        System.out.println("HandleCollisions");
        for (Ball ball : balls) {
            if (Collision.isBallOutsideBoard(this, ball)) {
                System.out.println("Notify: Ball outside");
                notifyBallExitBoard(ball);
                respawnBall(ball);
            }
            for (Brick brick : bricks) {
                if ( ball.distance(brick.getBody()) < ADJUST_COLLISION_DISTANCE ) {
                    notifyBallHitBrick(ball, brick);
                    System.out.println("Ball hit brick.");
                    bounceBall(ball, getRectDeflectionAngle(
                            ball, brick.getX(), brick.getY(), brick.getWidth(), brick.getHeight()));
                }
            }
            for (Player player : players) {
                if ( ball.distance(player.getPad().getBody()) < ADJUST_COLLISION_DISTANCE ) {
                    notifyBallHitPlayerPad(ball, player);
                    Pad pad = player.getPad();
                    System.out.printf("Ball hit player(x=%.1f, y=%.1f, w=%.1f, len=%.1f)\n",
                            pad.getPadXPos(), pad.getPadYPos(), pad.getWidth(), pad.getLength());
                    bounceBall(ball, getRectDeflectionAngle(
                            ball, pad.getPadXPos(), pad.getPadYPos(), pad.getWidth(), pad.getLength()));
                }
            }
            for (Ball otherBall : balls) {
                if (!ball.equals(otherBall) && ball.distance(otherBall.getBody()) < ADJUST_COLLISION_DISTANCE ) {
                    notifyBallHitBall(ball, otherBall);
                    System.out.println("Ball hit ball.");
                    bounceBall(ball, getCircleDeflectionAngle(ball, otherBall.getX(), otherBall.getY()));
                }
            }
        }
    }

    public void respawnBall(Ball b1) {
        Ball b2 = new Ball(lastBallModelAdded);
        balls.remove(b1);
        addBall(b2);
        System.out.printf("Board.respawnBall():\n    Old Ball(x=%.1f, y=%.1f, r=%.1f, a=%.1f, s=%.1f),\n    New Ball(x=%.1f, y=%.1f, r=%.1f, a=%.1f, s=%.1f)\n",
                b1.getX(), b1.getY(), b1.getRadius(), b1.getAngle(), b1.getSpeed(),
                b2.getX(), b2.getY(), b2.getRadius(), b2.getAngle(), b2.getSpeed() );
    }

    public float getCircleDeflectionAngle(Ball b1, float cirX, float cirY) {
        return (float)(Math.atan2( b1.getY() - cirY, b1.getX() - cirX ) + Math.PI/2f);
    }

    // TODO: Initial test seems to be correct.
    public float getRectDeflectionAngle(Ball ball, float recX, float recY, float recW, float recH) {
        float px = recX + Math.min(recW/2f, Math.max(-recW/2f, ball.getX() - recX));
        float py = recY + Math.min(recH/2f, Math.max(-recH/2f, ball.getY() - recY));
        float pAngle = (float)(Math.atan2( ball.getY() - py, ball.getX() - px ) + Math.PI/2f);
        /*System.out.printf("Deflect Ball(x%.1f, y%.1f, r%.1f) vs Rec(x%.1f, y%.1f, w%.1f, h%.1f) -> Edge(x%.1f, y%.1f, a%.1f)\n",
                ball.getX(), ball.getY(), ball.getRadius(),
                recX, recY, recW, recH,
                px, py, Math.toDegrees(pAngle) );
        */
        return pAngle;
    }

    // TODO: Test of math seems correct, further testing needed.
    public void bounceBall(Ball ball, float deflectionAngle) {
        float angleDiff = (ball.getAngle() - deflectionAngle + PIx2) % PIx2;
        System.out.printf("Bounce(x=%.0f, y=%.0f) towards %.0f deg, hitting %.0f deg. Angle difference = %.0f deg. ",
                ball.getX(), ball.getX(),
                Math.toDegrees(deflectionAngle),
                Math.toDegrees(ball.getAngle()),
                Math.toDegrees(angleDiff) );
        if (angleDiff >= 0 && angleDiff <= (float)Math.PI) {
            ball.setAngle( 2f*deflectionAngle-ball.getAngle() );
            System.out.printf("New angle = %.1f deg.\n",
                    Math.toDegrees(ball.getAngle()) );
        }
        else {
            System.out.println( "DIRECTION UNCHANGED" );
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
