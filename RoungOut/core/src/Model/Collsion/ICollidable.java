package Model.Collsion;

import javafx.geometry.Point2D;

/**
 * Model.Collsion.ICollidable Interface
 * Interface for all things collidable
 *
 * @author Ken Bäcklund
 */
public interface ICollidable {
    Point2D getPosition();                              // Object's current position
    Point2D getDirection();                             // Object's current direction
    boolean isCollision(ICollidable collidable);        // Did objects collide with each other?
    double getCollisionAngle(ICollidable collidable);   // Get object's collision angle
}
