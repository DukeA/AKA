package Model.Collision;

/**
 * @author Ken Bäcklund
 */
public interface CollisionObservable {

    void addCollisionObserver(CollisionObserver observer);
    void removeCollisionObserver(CollisionObserver observer);
}
