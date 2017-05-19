package Model.Collsion;

/**
 * Created by kendu on 2017-05-15.
 */
public interface CollisionObservable {

    void addCollisionObserver(CollisionObserver observer);
    void removeCollisionObserver(CollisionObserver observer);
}
