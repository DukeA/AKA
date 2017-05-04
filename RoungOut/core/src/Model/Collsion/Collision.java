package Model.Collsion;

import Model.GameObjects.Ball;
import Model.GameObjects.IBodyStateChangeObservable;
import Model.GameObjects.IBodyStateChangeObserver;
import Model.GameObjects.Physics.Body;
import Model.GameObjects.Physics.CircleBody;

import java.util.HashSet;
import java.util.Set;

/**
 * @author Ken Bäcklund
 */
public class Collision implements IBodyStateChangeObserver {

    private CircleBody board;       // TODO:  Temporary solution, will use proper Board class later.
    private Set<Body> bodies;

    public Collision() {
        board = new CircleBody(0f, 0f, 1000f);  // TODO: Use proper board class
        bodies = new HashSet<Body>();
    }

    public boolean isCollision(Body someBody, Body anyBody) {
        return (someBody.distance(anyBody) <= 0.0001f);
    }

    public boolean isOutsideBoardRange(Ball ball, double boardRadius) {
        board.setRadius(boardRadius);   // TODO: Temporary solution
        return (ball.distance(board) >= 0.0001f);
    }

    public void observeStateChanges(IBodyStateChangeObservable observable) {
        if (observable != null) {
            bodies.add((Body)observable);          // Remember all observable objects
            observable.addListener(this);   // Listen for any changes.
        }
    }

    public void stopObserveStateChanges(IBodyStateChangeObservable observable) {
        if (observable != null) {
            bodies.remove(observable);
            observable.removeListener(this);
        }
    }

    public void onStateChange(Body body) {
        // Something changed, such as a new position or angle.
        // Notification is done just *before* a move in order to handle bounce for very fast moving objects.
        // We need to check if a collision is about to occur.
        for (Body otherBody : bodies) {
            if (otherBody != null && !otherBody.equals(body)) {
                double distance = body.distance(otherBody);
                if (distance <= body.getSpeed()) {
                    // Collision is just about to occur

                }
            }
        }
    }
}
