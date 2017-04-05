import javafx.geometry.Point2D;

import java.awt.*;

/**
 * Ball Class
 * Handles basic ball action
 *
 * @author Ken Bäcklund
 */
public class Ball {

    private Point2D position;   // Ball's current position
    private Point2D direction;  // Ball's current direction
    private double radius;      // Ball's radius.

    // Constructor
    // Set ball's position, direction, and radius.
    Ball(Point2D position, Point2D direction, double radius) {
        setPosition(position);

        this.position =  new Point2D(position.getX(),  position.getY());
        this.direction = new Point2D(direction.getX(), direction.getY());
        this.radius = Math.abs(radius);     // No negative radius.
    }

    // Get ball's current position
    public Point2D getPosition() {
        return new Point2D(position.getX(),  position.getY());
    }

    // Get Ball's current position
    public void setPosition(Point2D position) {
        this.position =  new Point2D(position.getX(),  position.getY());
    }

    // Get ball's current direction
    public Point2D getDirection() {
        return new Point2D(direction.getX(),  direction.getY());
    }

    // Get ball's current position
    public void setDirection(Point2D direction) {
        this.direction =  new Point2D(direction.getX(),  direction.getY());
    }

    // Get ball's calculated next position
    public Point2D getNextPosition() {
        return getPosition().add(direction);
    }

    public void moveBall() {
        setPosition( position.add(direction) );
    }

    // Get ball's angle in degrees.
    public double getAngle() {
        return position.angle(direction);
    }

    // Did ball collide with a stationary rectangular object?
    // TODO - Add support for extreme speeds, movable objects, non-rectangular objects.
    public boolean isCollision(Point2D otherPosition, double width, double height) {
        double distance = position.distance(otherPosition);
        return distance <= radius ||
               distance <= (width / 2.0f) ||
               distance <= (height / 2.0f);
    }

    public void bounce(double angle) {

    }

}
