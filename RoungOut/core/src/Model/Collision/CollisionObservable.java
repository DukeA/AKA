package Model.Collision;

/**
 * Interface for observers to listen to board collision events.
 * @author Ken Bäcklund
 */
public interface CollisionObservable {

    /**
     * Add a new collision observer to the list.
     * @param observer the collision obersever to add.
     */
    void addCollisionObserver(CollisionObserver observer);

    /**
     * Remove a collision observer from the list.
     * @param observer the collision observer to remove.
     */
    void removeCollisionObserver(CollisionObserver observer);
}
