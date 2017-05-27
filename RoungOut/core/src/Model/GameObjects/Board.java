package Model.GameObjects;


import Model.Collision.Collision;
import Model.Collision.CollisionObserver;
import Model.GameObjects.Physics.Body;
import Model.GameObjects.Physics.CircleBody;

import java.util.*;

/**
 * Board class is the central class handling models and game logic.
 *
 * @author Ken Bäcklund
 * @author Adam Granden
 */
public class Board implements IBoard, IPowerUp {

    private static float DISTANCE_TOLERANCE = 0.01f;

    // Width, height, radius, and position of the board
    protected final int WIDTH;
    protected final int HEIGHT;
    private final float radius;
    private final Collision collision;
    private final float xPos;
    private final float yPos;
    private CircleBody board;
    // Last ball added serves as a model for new balls spawned.
    private Ball lastBallSpawned;

    // Estimated collision time, based on balls and obstacles.
    private double nextCollisionTime;

    // Collection if balls, bricks, players, observers.
    private Set<Ball> balls;
    private Set<Brick> bricks;
    private ArrayList<Player> players;
    private Set<CollisionObserver> observers;



    // Constructor ////////////////////////////////////////////////////////////

    /**
     * Create a board with a given width and height.
     * FIXME also populates sample date. Might or might not have to do it elsewhere.
     * @param width width of the board.
     * @param height height of the board.
     */
    public Board(int width, int height) {
        this.WIDTH = width;
        this.HEIGHT = height;
        xPos = width / 2;
        yPos = height / 2;
        radius = height / 2 + 100;
        board = new CircleBody(xPos, yPos, radius);
        balls = new HashSet<Ball>();
        bricks = new HashSet<Brick>();
        players = new ArrayList<Player>();
        collision = new Collision();
        observers = new HashSet<CollisionObserver>();
        createSampleBoard();
        nextCollisionTime = collision.estimateNextCollision(this);
    }
    /*

    // TODO Temporary mock data
    public void createSampleBoard(int WIDTH, int HEIGHT) {
        float PadLength =80f;
        float PadWidth =30f;
        float BrickWidth =30f;
        float BrickLength =30f;
        float BallRdius = 30f;
        this.addPlayer(new Player(PadLength, PadWidth, WIDTH/2,HEIGHT/2,
                WIDTH / 2 - 350, HEIGHT / 2, 1));
        this.addPlayer(new Player(PadLength, PadWidth,WIDTH/2,HEIGHT/2,
                WIDTH / 2 - 450, HEIGHT / 2, 1));


        this.addBrick(new Brick(WIDTH / 2 - 40-BrickWidth/2, HEIGHT / 2-BrickLength/2
                , BrickWidth, BrickLength));
        this.addBrick(new Brick(WIDTH / 2 - 40-BrickWidth/2, HEIGHT / 2-BrickLength/2
                , BrickWidth, BrickLength));
        this.addBrick(new Brick(WIDTH / 2-BrickWidth/2, HEIGHT / 2-BrickLength/2
                , BrickWidth, BrickLength));
        this.addBrick(new SDownBrick(WIDTH / 2 + 40-BrickWidth/2, HEIGHT / 2-BrickLength/2
                , BrickWidth, BrickLength));
        this.addBrick(new Brick(WIDTH / 2 - 40-BrickWidth/2, HEIGHT / 2 - 40-BrickLength/2
                , BrickWidth, BrickLength));
        this.addBrick(new Brick(WIDTH / 2-BrickWidth/2, HEIGHT / 2 - 40-BrickLength/2
                , BrickWidth, BrickLength));
        this.addBrick(new Brick(WIDTH / 2 + 40-BrickWidth/2, HEIGHT / 2 - 40-BrickLength/2
                , BrickWidth, BrickLength));
        this.addBrick(new SUpBrick(WIDTH / 2 - 40-BrickWidth/2, HEIGHT / 2 + 40-BrickLength/2
                , BrickWidth, BrickLength));
        this.addBrick(new Brick(WIDTH / 2-BrickWidth/2, HEIGHT / 2 + 40-BrickLength/2
                , BrickWidth, BrickLength));
        this.addBrick(new Brick(WIDTH / 2 + 40-BrickWidth/2, HEIGHT / 2 + 40-BrickLength/2
                , BrickWidth, BrickLength));

        this.addBall(new Ball(WIDTH / 2 - 250-BallRdius/2, HEIGHT / 2 + 20-BallRdius/2
                , BallRdius, 1, 100));
    }

    private float correctAngle(float radians) {
        while (radians < 0) radians += Math.PI * 2f;
        return radians % (float)(Math.PI * 2f);
    }*/

    /**
     * Get board X position.
     * @return the board X position.
     */
    public float getXPos() {
        return this.xPos;
    }

    /**
     * Get board Y position
     * @return the board Y position.
     */
    public float getYPos() {
        return this.yPos;
    }

    /**
     * Get board radius
     * @return the board radius.
     */
    public float getRadius() {
        return this.radius;
    }

    /**
     * Get set of balls added to the board.
     * @return a Set of balls.
     */
    public Set<Ball> getBalls() {
        return balls;
    }

    /**
     * Get set of bricks added to the board.
     * @return a Set of bricks.
     */
    public Set<Brick> getBricks() {
        return bricks;
    }

    /**
     * Get players added to the board.
     * @return the players added to the board.
     */
    public ArrayList<Player> getPlayers() {
        return players;
    }

    /**
     * Update the board by moving balls and checking collisons.
     * @param deltaTime the time frame to move balls by.
     */
    public void update(float deltaTime) {

        // While we still have time left..
        while (deltaTime > 0) {
            // Calculate time until next collision will occur.
            float minTime = (float)Math.min( deltaTime, nextCollisionTime);
            for (Ball ball : balls) {
                // Move each ball by the minimum time estimated.
                ball.move(minTime);
            }
            deltaTime -= minTime;
            nextCollisionTime -= minTime;

            // If we still have time left, we most likely have an ongoing collision.
            if (deltaTime > 0) {
                // Handle collisions and estimate a new time until next collision.
                handleCollisions();
                nextCollisionTime = collision.estimateNextCollision(this);

                // If estimated time is at zero, we might have a stuck ball. Try untangle it.
                if (nextCollisionTime < 0.001f) {
                    untangleBalls();
                }
            }
        }
    }

    /**
     * Add a new ball to the board.
     * @param ball the ball to add.
     */
    public void addBall(Ball ball) {
        if (ball == null) {
            throw new IllegalArgumentException("Cannot add a ball to the board when it's NULL.");
        }

        // Add new ball, also use it as a template for when balls will respawn.
        this.balls.add(ball);
        lastBallSpawned = new Ball(ball);

        // Estimate new collision time.
        // Throw an exception if we adding the ball caused an immediate collision.
        nextCollisionTime = collision.estimateNextCollision(this);
        if (nextCollisionTime < DISTANCE_TOLERANCE) {
            throw new IllegalArgumentException(String.format(
                    "Placing ball at (%.3f, %.3f) cause an immediate collision.",
                    ball.getX(), ball.getY()) );
        }
    }

    /**
     * Add a brick to the board.
     * @param brick the brick to add.
     */
    public void addBrick(Brick brick) {
        if (brick == null) {
            throw new IllegalArgumentException("Cannot add a brick to the board when it's NULL.");
        }
        // Add brick, make sure it's not causing an immediate collision.
        this.bricks.add(brick);
        nextCollisionTime = collision.estimateNextCollision(this);
        if (nextCollisionTime < 0.0001f) {
            throw new IllegalArgumentException(String.format(
                    "Placing brick at (%.3f, %.3f) cause an immediate collision.",
                    brick.getX(), brick.getY()) );
        }
    }

    /**
     * Add a player to the board.
     * @param player the player to add.
     */
    public void addPlayer(Player player) {
        if (player == null) {
            throw new IllegalArgumentException("Cannot add a player to the board when it's NULL.");
        }

        // Add player, make sure it's not causing an immediate collision.
        this.players.add(player);
        nextCollisionTime = collision.estimateNextCollision(this);
        if (nextCollisionTime < 0.0001f) {
            throw new IllegalArgumentException(String.format(
                    "Placing player at (%.3f, %.3f) cause an immediate collision.",
                    player.getPad().getPadXPos(), player.getPad().getPadYPos()) );
        }
    }

    /**
     * Get the distance for a ball from center of the board.
     * @param ball the ball to check.
     * @return the distance from center of the board.
     */
    public float distanceFromCenter(Ball ball) {
        double dx = ball.getX() - xPos;
        double dy = ball.getY() - yPos;
        return (float)Math.sqrt(dx*dx+dy*dy);
    }

    /**
     * Notify observers that a ball went outside the board.
     * @param ball the ball that went outside the board.
     */
    public void notifyBallExitBoard(Ball ball) {
        for (CollisionObserver observer : observers) {
            observer.onBallExitBoard(ball);
        }
    }

    /**
     * Notify observers that a ball hit a brick.
     * @param ball the ball that hit the brick.
     * @param brick the brick that got hit.
     */
    public void notifyBallHitBrick(Ball ball, Brick brick) {
        for (CollisionObserver observer : observers) {
            observer.onBallHitBrick(ball, brick);
        }
    }

    /**
     * Notify observers that a ball hit a player pad.
     * @param ball the ball that hit the player pad.
     * @param player the player (and it's related pad) that got hit.
     */
    public void notifyBallHitPlayerPad(Ball ball, Player player) {
        for (CollisionObserver observer : observers) {
            observer.onBallHitPlayerPad(ball, player);
        }
    }

    /**
     * Notify observers that a ball hit another ball.
     * @param ball the ball that got hit.
     * @param otherBall the other ball that got hit.
     */
    public void notifyBallHitBall(Ball ball, Ball otherBall) {
        for (CollisionObserver observer : observers) {
            observer.onBallHitBall(ball, otherBall);
        }
    }

    /**
     * Add a new collision observer.
     * @param observer the collision observer to add.
     */
    public void addCollisionObserver(CollisionObserver observer) {
        if (observer != null) {
            observers.add(observer);
        }
    }

    /**
     * Remove a collision observer.
     * @param observer the collision observer to remove.
     */
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


    // FIXME - Adam, we should not use reflections to see what type of brick it is.
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


    // FIXME - Adam, we should not use reflections to see what type of brick it is.
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



    // Private helper methods /////////////////////////////////////////////////

    /**
     * Corrects an angle so it stays within range (0..2*PI).
     * @param radians the angle to correct.
     * @return radians within range (0..2*PI).
     */
    private float correctAngle(float radians) {
        while (radians < 0) radians += Math.PI * 2f;
        return radians % (float)(Math.PI * 2f);
    }

    /**
     * Bounce ball given a deflection angle. Ignore if angles are wrong.
     * @param ball the ball to bounce.
     * @param deflectionAngle the angle to deflect ball at.
     */
    private void bounceBall(Ball ball, float deflectionAngle) {
        // Check if ball and deflection angle are less than 180.
        deflectionAngle = correctAngle(deflectionAngle);
        float aDiff = deflectionAngle - ball.getDirection();
        float a1 = (float)Math.toDegrees(ball.getDirection());
        float a2 = (float)Math.toDegrees(  (ball.getDirection() + 2f * aDiff) % (2f*Math.PI) );
        // Only bounce if the angle diffrence are within a given range.
        if (aDiff > 0 || aDiff < -Math.PI) {
            // If ball is deflected, set a new angle.
            ball.setDirection( ball.getDirection() + 2f * aDiff );
        }
    }

    /**
     * Search and handle collisions.
     * Notify observers of events.
     * Respawn ball falling outside board.
     * Bounce ball against objects.
     */
    private void handleCollisions() {

        // Check every ball against every object.
        for (Ball ball : balls) {

            // Ball outside board? Notify observer and respawn ball.
            if (collision.isBallOutsideBoard(this, ball)) {
                notifyBallExitBoard(ball);
                respawnBall(ball);
            }

            // If brick hit, mark it as destroyed. Ignore previously destroyed bricks.
            // TODO trigger any special bricks.
            for (Brick brick : bricks) {
                if (brick.isDestroyed()) {
                    continue;
                }
                if ( ball.distance(brick.getBody()) < DISTANCE_TOLERANCE) {
                    notifyBallHitBrick(ball, brick);
                    untangleBall(ball, brick.getBody());
                    bounceBall(ball, (float)collision.getRectangleDeflectionAngle(
                            ball.getX(), ball.getY(), brick.getX(), brick.getY(), brick.getWidth(), brick.getHeight()));
                    brick.markDestroyed();
                }
            }
            // If a player pad is hit, notify observers and bounce ball.
            // TODO Add score?
            for (Player player : players) {
                if ( ball.distance(player.getPad().getBody()) < DISTANCE_TOLERANCE) {
                    notifyBallHitPlayerPad(ball, player);
                    Pad pad = player.getPad();
                    untangleBall(ball, pad.getBody());
                    bounceBall(ball, (float)collision.getRectangleDeflectionAngle(
                            ball.getX(), ball.getY(), pad.getPadXPos(), pad.getPadYPos(), pad.getWidth(), pad.getLength()));
                }
            }
            // If another ball hit, notify observers and bounce the ball.
            for (Ball otherBall : balls) {
                if (!ball.equals(otherBall) && ball.distance(otherBall.getBody()) < DISTANCE_TOLERANCE) {
                    notifyBallHitBall(ball, otherBall);

                    // Untangle balls and cause both to bounce.
                    untangleBall(ball, otherBall.getBody());
                    untangleBall(otherBall, otherBall.getBody());
                    bounceBall(ball, (float)collision.getCircleDeflectionAngle(ball.getX(), ball.getY(), otherBall.getX(), otherBall.getY()));
                    bounceBall(otherBall, (float)collision.getCircleDeflectionAngle(otherBall.getX(), otherBall.getY(), ball.getX(), ball.getY()));
                }
            }
            //ball.move(0.01f);
        }
    }

    /**
     * respawn a new ball, and remove the old ball from the board.
     * @param oldBall the ball to remove.
     */
    private void respawnBall(Ball oldBall) {
        Ball newBall = new Ball(lastBallSpawned);
        moveBallRandomly(newBall, radius * 0.5f);
        balls.remove(oldBall);
        addBall(newBall);
        nextCollisionTime = collision.estimateNextCollision(this);
    }

    /**
     * Move ball to a random location on the board with a given distance from the center.
     * @param ball the ball to move randomly.
     * @param distance distance from center of the board.
     */
    private void moveBallRandomly(Ball ball, float distance) {
        double a = 2*Math.PI + Math.random();
        ball.setPosition(
                xPos + distance * (float)Math.cos(a),
                yPos + distance * (float)Math.sin(a));
        // Set a random direction heading back towards the board.
        ball.setDirection((float)(a + Math.PI + Math.random() - 0.5f));
    }

    /**
     * Move all balls a small step backwards, in case of balls getting stuck.
     */
    private void untangleBalls() {
        for (Ball ball : balls) {
            ball.move(0.05f);
        }
    }

    /**
     * Untangle ball after a collision occurred.
     * @param ball
     * @param body
     */
    private void untangleBall(Ball ball, Body body) {
        // Move ball out of collision range before bounce in reverse direction.
        //System.out.printf("Adjusting position before bounce. Ball at x%.4f y%.4f\n", ball.getX(), ball.getY());
        while (ball.distance(body) <= 0) {
            ball.move(-0.05f);
        }
    }

    /*
     * Mock sample data to generate a playable board.
     * FIXME We might have to move this to another class.
     */
    private void createSampleBoard() {
        float padHeight =   80f;
        float padWidth =    30f;
        float brickWidth =  30f;
        float brickHeight = 30f;

        // Mock players
        this.addPlayer( new Player(
                padHeight, padWidth, WIDTH/2,HEIGHT/2,
                WIDTH / 2 - 350, HEIGHT / 2, 1));
        this.addPlayer( new Player(
                padHeight, padWidth,WIDTH/2,HEIGHT/2,
                WIDTH / 2 - 450, HEIGHT / 2, 1));

        // Mock Bricks
        this.addBrick(new Brick(
                WIDTH / 2 - 40-brickWidth/2, HEIGHT / 2-brickHeight/2,
                brickWidth, brickHeight));
        this.addBrick(new Brick(WIDTH / 2 - 40-brickWidth/2, HEIGHT / 2-brickHeight/2,
                brickWidth, brickHeight));
        this.addBrick(new Brick(WIDTH / 2-brickWidth/2, HEIGHT / 2-brickHeight/2,
                brickWidth, brickHeight));
        this.addBrick(new SDownBrick(WIDTH / 2 + 40-brickWidth/2, HEIGHT / 2-brickHeight/2,
                brickWidth, brickHeight));
        this.addBrick(new Brick(WIDTH / 2 - 40-brickWidth/2, HEIGHT / 2 - 40-brickHeight/2,
                brickWidth, brickHeight));
        this.addBrick(new Brick(WIDTH / 2-brickWidth/2, HEIGHT / 2 - 40-brickHeight/2,
                brickWidth, brickHeight));
        this.addBrick(new Brick(WIDTH / 2 + 40-brickWidth/2, HEIGHT / 2 - 40-brickHeight/2,
                brickWidth, brickHeight));
        this.addBrick(new SUpBrick(WIDTH / 2 - 40-brickWidth/2, HEIGHT / 2 + 40-brickHeight/2,
                brickWidth, brickHeight));
        this.addBrick(new Brick(WIDTH / 2-brickWidth/2, HEIGHT / 2 + 40-brickHeight/2,
                brickWidth, brickHeight));
        this.addBrick(new Brick(WIDTH / 2 + 40-brickWidth/2, HEIGHT / 2 + 40-brickHeight/2,
                brickWidth, brickHeight));

        // Mock ball
        this.addBall(new Ball(WIDTH / 2 - 250, HEIGHT / 2 + 20, 20f, (float)Math.random()-0.5f, 300));
    }

}
