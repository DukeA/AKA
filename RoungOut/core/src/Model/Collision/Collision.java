package Model.Collision;

import Model.GameObjects.*;
import Model.GameObjects.Physics.RectangleBody;

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



    // Time estimation on ball vs. any object /////////////////////////////////

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

        // Get shortest time until ball exits board.
        time = Math.min(time, estimateBallGone(board, ball));

        // Get shortest time until ball collide with any brick
        estTime = estimateBrickCollision(ball, board.getBricks());
        time = Double.isNaN(estTime) ? time : Math.min(time, estTime);

        // Get shortest time until ball collide with any other ball
        estTime = estimateBallCollision(ball, board.getBalls());
        time = Double.isNaN(estTime) ? time : Math.min(time, estTime);

        // Get shortest time until ball collide with any pad
        estTime = estimatePlayerCollision(ball, board.getPlayers());
        time = Double.isNaN(estTime) ? time : Math.min(time, estTime);

        // Return shortest time found.
        return Math.max(0, time);
    }



    // Time estimation on ball vs. ball collisions ////////////////////////////

    /**
     * Estimate the time (movement units) until balls collide.
     * @param ball the ball to check.
     * @param balls iterable set of balls to check ball against.
     * @return time (movement units) until next estimated collision.
     */
    public double estimateBallCollision(Ball ball, Iterable<Ball> balls) {
        double minTime = Double.MAX_VALUE; // Default is "never"
        // Iterate through each ball to find the shortest time left.
        for (Ball otherBall : balls) {
            // Don't compare a ball against itself.
            if (otherBall.equals(ball))
                continue;
            // Find the ball with shortest time until a collision occurs.
            double estTime = estimateBallCollision(ball, otherBall);
            minTime = Double.isNaN(estTime)
                    ? minTime : Math.min(estTime, minTime);
        }
        // Return shortest time found.
        return minTime;
    }

    /**
     * Estimate the time (movement units) until two balls collide.
     * @param ball the ball to check collision for.
     * @param otherBall the other ball to check against.
     * @return estimated time for collision. NaN if no collision.
     */
    public double estimateBallCollision(Ball ball, Ball otherBall) {
        if (ball.equals(otherBall))
            return Double.NaN;
        return estimateCircleCollisions(
                ball.getX() - otherBall.getX(),
                ball.getY() - otherBall.getY(),
                ball.getDeltaX() - otherBall.getDeltaX(),
                ball.getDeltaY() - otherBall.getDeltaY(),
                ball.getRadius() + otherBall.getRadius());
    }



    // Time estimation on ball vs. brick collisions ///////////////////////////

    /**
     * Find shortest time (movement units) until a ball collide with bricks.
     * @param ball the ball to check against.
     * @param bricks iterable collection of bricks.
     * @return the shortest time until next collision. NaN if no collision.
     */
    public double estimateBrickCollision(Ball ball, Iterable<Brick> bricks) {
        double minTime = Double.MAX_VALUE; // Default is "never"
        // Iterate through each brick to find the shortest time left.
        for (Brick brick : bricks) {
            // Find the brick with shortest time until a collision occurs.
            double estTime = estimateBrickCollision(ball, brick);
            minTime = Double.isNaN(estTime)
                    ? minTime : Math.min(estTime, minTime);
        }
        // Return shortest time found.
        return minTime;
    }

    /**
     * Estimate the time (movement units) until ball amd brick collide.
     * @param ball the ball to check collision for.
     * @param brick the brick to check against.
     * @return estimated time for collision. NaN if no collision.
     */
    public double estimateBrickCollision(Ball ball, Brick brick) {
        return estimateRectCollision(ball, brick.getBody());
    }



    // Time estimation on ball vs. player pad collisions //////////////////////

    /**
     * Find shortest time until a ball collide with a player pad.
     * @param ball the ball to check against.
     * @param players iterable collection of players (and their pads).
     * @return the shortest time until next collision. NaN if no collision.
     */
    public double estimatePlayerCollision(Ball ball, Iterable<Player> players) {
        double minTime = Double.MAX_VALUE; // Default is "never"
        // Iterate through each player's pad to find the shortest time left.
        for (Player player : players) {
            // Find the brick with shortest time until a collision occurs.
            double estTime = estimatePlayerCollision(ball, player);
            minTime = Double.isNaN(estTime)
                    ? minTime : Math.min(estTime, minTime);
        }
        // Return shortest time found.
        return minTime;
    }

    /**
     * Find shortest time until a ball collide with player pad.
     * @param ball the ball to check against.
     * @param player the player (and related pad) to check against.
     * @return the shortest time until next collision. NaN if no collision.
     */
    public double estimatePlayerCollision(Ball ball, Player player) {
        Pad pad = player.getPad();
        return estimateRectCollision(ball,
                new RectangleBody( pad.getPadXPos(), pad.getPadYPos(),
                                   pad.getWidth(), pad.getLength()));
    }


    // FIXME Ball seems to move a bit further past board edge than expected.

    /**
     * Estimate time (movement units) until ball is outside the board.
     * @param board the board to check against.
     * @param ball the ball to check distance on.
     * @return true if ball is outside board radius.
     */
    public double estimateBallGone(Board board, Ball ball) {
        double time = estimateCircleCollisions(
                ball.getX() - board.getXPos(),
                ball.getY() - board.getYPos(),
                ball.getDeltaX(),
                ball.getDeltaY(),
                ball.getRadius() + board.getRadius());
        if (Double.isNaN(time)) {
            return 0;   // No collision course, ball must be outside board
        } else if (Math.abs(time) < 0.02f) {
            return 0;   // Close enough to border, consider it gone.
        } else {
            return time;   // Else, give estimated time until ball outside board.
        }
    }



    // Private helper methods /////////////////////////////////////////////////

    /**
     * Estimate the time until two circular objects will collide.
     * @param relX relative X distance between the circles.
     * @param relY relative Y distance between the circles.
     * @param dx relative horizontal movement speed between circles.
     * @param dy relative vertical movement speed between circles.
     * @param totalRadius the total added radius of the circles.
     * @return estimated time until collision. NaN if no collision course.
     */
    private double estimateCircleCollisions(double relX, double relY,
                                           double dx, double dy,
                                           double totalRadius) {
        // Look up quadratic formula for the calculations below.
        double a = (dx*dx + dy*dy);
        double b = 2 * (relX*dx + relY*dy);
        double c = relX*relX + relY*relY - totalRadius*totalRadius;
        double d = b*b - 4*a*c;
        if (d < 0)
            return Float.NaN;

        // any positive t1 and t2 will give an estimated time.
        double t1 = (-b + Math.sqrt(d)) / (2 * a);
        double t2 = (-b - Math.sqrt(d)) / (2 * a);
        if (t1 < 0 && t2 < 0) {
            return Double.NaN;
        }
        if (t1 > 0 && t2 > 0)
            return Math.min(t1, t2);
        else
            return Math.max(t1, t2);
    }

    /**
     * Estimate time until ball collides with a rectangular body.
     * @param ball the ball to check.
     * @param rect the rectangleBody to check against.
     * @return estimated time until collision. NaN if no collision time.
     */
    private double estimateRectCollision(Ball ball, RectangleBody rect) {
        // Find nearest edge of rectangle
        double px = rect.getX() + Math.min(rect.getWidth()/2,
                Math.max(-rect.getWidth()/2, ball.getX() - rect.getX()));
        double py = rect.getY() + Math.min(rect.getHeight()/2,
                Math.max(-rect.getHeight()/2, ball.getY() - rect.getY()));
        double time = estimateCircleCollisions(
                ball.getX() - px,
                ball.getY() - py,
                ball.getDeltaX(),
                ball.getDeltaY(),
                ball.getRadius());
        return time;
    }
}
