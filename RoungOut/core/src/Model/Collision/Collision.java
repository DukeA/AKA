package Model.Collision;

import Model.GameObjects.*;
import Model.GameObjects.Physics.RectangleBody;

import java.util.Collection;

/**
 * Collision methods between board, ball, bricks, and pads.
 *
 * @author Ken Bäcklund
 */
public class Collision {

    // Public methods /////////////////////////////////////////////////////////

    /**
     * Get the deflection angle between two circles
     * @param circleX1 center X position of first circle.
     * @param circleY1 center Y position of first circle.
     * @param circleX2 center X position of second circle.
     * @param circleY2 center Y position of second circle.
     * @return angle of deflection between closest points of both circles.
     */
    public double getCircleDeflectionAngle(float circleX1, float circleY1,
                                           float circleX2, float circleY2) {
        // Calculate angle of deflection. Assuming circles do not intersect.
        return (Math.atan2( circleY1 - circleY2, circleX1 - circleX2 )
                + Math.PI/2)%(2*Math.PI);
    }

    /**
     * Get the deflection angle between a point and a rectangle.
     * @param pX X position of a point.
     * @param pY Y position of a point.
     * @param recX rectangle's center X position.
     * @param recY rectangle's center Y position.
     * @param recW width of the rectangle.
     * @param recH height of the rectangle.
     * @return angle of deflection between point and edge of the rectangle.
     */
    public double getRectangleDeflectionAngle(float pX, float pY,
                                             float recX, float recY,
                                             float recW, float recH) {
        // Find nearest edge towards the point.
        double recEdgeX = recX + Math.min(recW/2,
                                          Math.max(-recW/2, pX - recX));
        double recEdgeY = recY + Math.min(recH/2,
                                          Math.max(-recH/2, pY - recY));
        // Calculate angle of deflection.
        double angle = (Math.atan2( recEdgeY-pY, recEdgeX-pX )
                + Math.PI/2)%(2*Math.PI);
        return angle;
    }

    /**
     * Return true if the ball is outside the board radius.
     * @param board the board ball must stay within.
     * @param ball the ball to check against the board.
     * @return true if ball is outside board's circle radius.
     */
    public boolean isBallOutsideBoard(Board board, Ball ball) {
        // Get delta position between ball and board.
        double dX = ball.getX() - board.getXPos();
        double dY = ball.getY() - board.getYPos();
        double totalRadius = ball.getRadius() + board.getRadius();
        // Calculate distance between center of circles minus total radius.
        double dz = (dX*dX + dY*dY) - totalRadius*totalRadius;
        // Return true of the distance is positive (no intersection).
        return dz >= 0;
    }

    /**
     * Estimate the time (movement units) until any collision occurs.
     * @param board the board to check.
     * @return time (movement units) until next estimated collision.
     */
    public double estimateNextCollision(Board board) {
        double time = Double.MAX_VALUE;      // Default time is "never".
        for (Ball ball : board.getBalls()) {
            // Get the shortest time between each ball.
            time = Math.min(time,
                    estimateNextCollision(board, ball));
        }
        // Return shortest time. time < 0, assume immediate collision.
        return Math.max(0, time);
    }

    /**
     * Estimate the time (movement units) until a ball collision.
     * @param board the board to check.
     * @param ball the ball to check against the board.
     * @return time (movement units) until next estimated collision.
     */
    public double estimateNextCollision(Board board, Ball ball) {
        double time = Double.MAX_VALUE;    // Default time is "never".
        double estTime;

        // Shortest time until ball(s) exit board.
        time = Math.min(time, estimateBallGone(board, ball));

        //TODO Separation of Concerns
        // Shortest time until ball(s) collide with any brick
        for (Brick brick : board.getBricks()) {
            estTime = estimateBrickCollision(ball, brick);
            time = Double.isNaN(estTime)
                    ? time : Math.min(time, estTime);
        }

        //TODO Separation of Concerns
        // Shortest time until ball(s) collide with any other ball
        for (Ball otherBall : board.getBalls()) {
            if (!ball.equals(otherBall)) {
                estTime = estimateBallCollision(ball, otherBall);
                time = Double.isNaN(estTime)
                        ? time : Math.min(time, estTime);
            }
        }

        // TODO Separation of Concerns
        // Shortest time until ball(s) collide with any pad
        for (Player player : board.getPlayers()) {
            estTime = estimatePadCollision(ball, player.getPad());
            time = Double.isNaN(estTime)
                    ? time : Math.min(time, estTime);
        }

        return (time < 0) ? 0 : time;
    }

    // Shortest deltaTime until ball(s) collide with any other ball
    public float estimateBallCollision(Ball b1, Ball b2) {
        return estimateCircleCollisions(
                b1.getX() - b2.getX(),
                b1.getY() - b2.getY(),
                b1.getDeltaX() - b2.getDeltaX(),
                b1.getDeltaY() - b2.getDeltaY(),
                b1.getRadius() + b2.getRadius());
    }

    /**
     * Find shortest time (movement units) until a ball collide with bricks.
     * @param ball the ball to check against.
     * @param bricks iterable collection of bricks.
     * @return the shortest time until next collision. NaN if no collision.
     */
    public double estimateBrickCollision(Ball ball, Iterable<Brick> bricks) {
        // Iterate through each brick to find the shortest time left.
        double minTime = Double.MAX_VALUE; // Default is "never"
        for (Brick brick : bricks) {
            double time = estimateBrickCollision(ball, brick);
            minTime = Double.isNaN(time)
                    ? time : Math.min(time, minTime);
        }
        // Return shortest time found.
        return minTime;
    }

    // Shortest deltaTime until ball(s) hit a brick. Return NaN if not on a collision course.
    public double estimateBrickCollision(Ball ball, Brick brick) {
        return estimateRectCollision(ball, brick.getBody());
    }

    // Estimate deltaTime until a ball exits board.
    public float estimateBallGone(Board board, Ball ball) {
        //System.out.printf("estimateBallGone: Ball at (x=%.2f, y=%.2f)\n",ball.getX(), ball.getY());
        float t = estimateCircleCollisions(
                ball.getX() - board.getXPos(),
                ball.getY() - board.getYPos(),
                ball.getDeltaX(),
                ball.getDeltaY(),
                ball.getRadius() + board.getRadius());
        if (Float.isNaN(t)) {
            return 0;   // No collision course, ball must be outside board
        } else if (Math.abs(t) < 0.02f) {
            return 0;   // Close enough to border, consider it gone.
        } else {
            return t;   // Else, give estimated time until ball outside board.
        }
    }

    // Shortest deltaTime until ball(s) hit a pad. Return NaN if no collision course.
    public float estimatePadCollision(Ball ball, Pad pad) {
        return estimateRectCollision(ball,
                new RectangleBody(pad.getPadXPos(),pad.getPadYPos(), pad.getWidth(), pad.getLength()));
    }

    // Estimate deltaTime until two circular objects collide.
    //     px,py:  Relative distance between circles.
    //     dx,dy:  Relative movement between circles.
    //     rr:     Radius of both circles added together
    //   Returns:  deltaTime if collision course, NaN if no collision course.
    public float estimateCircleCollisions(float px, float py, float dx, float dy, float rr) {
        // Look up quadratic formula for the calculations below.
        float a = (dx * dx + dy * dy);
        float b = 2 * (px * dx + py * dy);
        float c = px * px + py * py - rr * rr;
        float d = b * b - 4 * a * c;
        if (d < 0)
            return Float.NaN;

        float t1 = (-b + (float) Math.sqrt(d)) / (2 * a);
        float t2 = (-b - (float) Math.sqrt(d)) / (2 * a);
        if (t1 < 0 && t2 < 0) {
            return Float.NaN;
        }
        if (t1 > 0 && t2 > 0)
            return Math.min(t1, t2);
        else
            return Math.max(t1, t2);
    }

    public float estimateRectCollision(Ball b, RectangleBody r) {
        // Find nearest edge of rectangle
        float px = r.getX() + Math.min(r.getWidth()/2f, Math.max(-r.getWidth()/2f, b.getX() - r.getX()));
        float py = r.getY() + Math.min(r.getHeight()/2f, Math.max(-r.getHeight()/2f, b.getY() - r.getY()));
        float t = estimateCircleCollisions(
                b.getX() - px,
                b.getY() - py,
                b.getDeltaX(),
                b.getDeltaY(),
                b.getRadius());
        return t;
    }
    public Collision() {
    }
}
