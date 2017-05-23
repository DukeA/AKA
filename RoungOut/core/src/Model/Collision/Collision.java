package Model.Collision;

import Model.GameObjects.*;
import Model.GameObjects.Physics.RectangleBody;

import java.util.*;

/**
 * Static collision methods between board, ball, bricks, and pads.
 *
 * @author Ken Bäcklund
 */
public class Collision {


    // Public methods /////////////////////////////////////////////////////////

    public static float getCircleDeflectionAngle(float circleX1, float circleY1, float circleX2, float circleY2) {
        return (float)(Math.atan2(
                circleY1 - circleY2, circleX1 - circleX2 ) + Math.PI/2f);
    }

    public static float getRectDeflectionAngle(float px, float py, float recX, float recY, float recW, float recH) {
        float xCollision = recX + Math.min(recW/2f, Math.max(-recW/2f, px - recX));
        float yCollision = recY + Math.min(recH/2f, Math.max(-recH/2f, py - recY));
        return (float)(Math.atan2( py - yCollision, px - xCollision ) + Math.PI/2f);
    }

    public static boolean isBallOutsideBoard(Board board, Ball ball) {
        float dx = ball.getX() - board.getXPos();
        float dy = ball.getY() - board.getYPos();
        float dz = (float) Math.sqrt(dx * dx + dy * dy) - (ball.getRadius() + board.getRadius());
        return Math.abs(dz) <= 0.01f;  // That's close enough.
    }

    // Estimate min deltaTime until any form of collision occurs.
    public static float estimateNextCollision(Board board) {
        float time = Float.MAX_VALUE;        // Default time is "never".
        for (Ball ball : board.getBalls()) {
            time = Math.min(time, estimateTimeSingleBallCollision(board, ball));
        }
        // Shortest time estimated. Best to save/decrement this value from deltaTime.
        return time;
    }

    // Helper methods /////////////////////////////////////////////////////////
    public static float estimateTimeSingleBallCollision(Board board, Ball ball) {
        float time = Float.MAX_VALUE;        // Default time is "never".
        float estTime;

        // Shortest time until ball(s) exit board.
        time = Math.min(time, estimateBallGone(board, ball));

        // Shortest time until ball(s) hit a brick.
        for (Brick brick : board.getBricks()) {
            estTime = estimateBrickCollision(ball, brick);
            time = Float.isNaN(estTime)
                    ? time : Math.min(time, estTime);
        }

        // Shortest time until ball(s) collide with any other ball
        for (Ball otherBall : board.getBalls()) {
            if (!ball.equals(otherBall)) {
                estTime = estimateBallCollision(ball, otherBall);
                time = Float.isNaN(estTime)
                        ? time : Math.min(time, estTime);
            }
        }

        // Shortest time until ball(s) collide with any pad
        for (Player player : board.getPlayers()) {
            estTime = estimatePadCollision(ball, player.getPad());
            time = Float.isNaN(estTime)
                    ? time : Math.min(time, estTime);
        }

        return time;
    }

    // Shortest deltaTime until ball(s) collide with any other ball
    public static float estimateBallCollision(Ball b1, Ball b2) {
        return estimateCircleCollisions(
                b1.getX() - b2.getX(),
                b1.getY() - b2.getY(),
                b1.getDeltaX() - b2.getDeltaX(),
                b1.getDeltaY() - b2.getDeltaY(),
                b1.getRadius() + b2.getRadius());
    }

    // Shortest deltaTime until ball(s) hit a brick. Return NaN if not on a collision course.
    public static float estimateBrickCollision(Ball ball, Brick brick) {
        return estimateRectCollision(ball, brick.getBody());
    }

    // Estimate deltaTime until a ball exits board.
    public static float estimateBallGone(Board board, Ball ball) {
        //System.out.printf("estimateBallGone: Ball at (x=%.2f, y=%.2f)\n",ball.getX(), ball.getY());
        float t = estimateCircleCollisions(
                ball.getX() - board.getXPos(),
                ball.getY() - board.getYPos(),
                ball.getDeltaX(),
                ball.getDeltaY(),
                ball.getRadius() + board.getRadius());
        if (Float.isNaN(t)) {
            return 0;   // No collision course, ball must be outside board
        } else if (Math.abs(t) < 0.01f) {
            return 0;   // Close enough to border, consider it gone.
        } else {
            return t;   // Else, give estimated time until ball outside board.
        }
    }

    // Shortest deltaTime until ball(s) hit a pad. Return NaN if no collision course.
    public static float estimatePadCollision(Ball ball, Pad pad) {
        return estimateRectCollision(ball,
                new RectangleBody(pad.getPadXPos(),pad.getPadYPos(), pad.getWidth(), pad.getLength()));
    }

    // Estimate deltaTime until two circular objects collide.
    //     px,py:  Relative distance between circles.
    //     dx,dy:  Relative movement between circles.
    //     rr:     Radius of both circles added together
    //   Returns:  deltaTime if collision course, NaN if no collision course.
    public static float estimateCircleCollisions(float px, float py, float dx, float dy, float rr) {
        // Look up quadratic formula for the calculations below.
        float a = (dx * dx + dy * dy);
        float b = 2 * (px * dx + py * dy);
        float c = px * px + py * py - rr * rr;
        float d = b * b - 4 * a * c;
        if (d < 0)
            return Float.NaN;

        float t1 = (-b + (float) Math.sqrt(d)) / (2 * a);
        float t2 = (-b - (float) Math.sqrt(d)) / (2 * a);
        if (t1 < 0 && t2 < 0)
            return Float.NaN;
        if (t1 < 0 || t2 < 0)
            return Math.max(t1, t2);
        else
            return Math.min(t1, t2);
    }

    public static float estimateRectCollision(Ball b, RectangleBody r) {
        // Find nearest edge of rectangle
        float px = r.getX() + Math.min(r.getWidth()/2f, Math.max(-r.getWidth()/2f, b.getX() - r.getX()));
        float py = r.getY() + Math.min(r.getHeight()/2f, Math.max(-r.getHeight()/2f, b.getY() - r.getY()));

        // Now we can use circle estimation method towards a set point.
        return estimateCircleCollisions(
                b.getX() - px,
                b.getY() - py,
                b.getDeltaX(),
                b.getDeltaY(),
                b.getRadius());
    }
}
