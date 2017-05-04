package Model.Collsion;

/**
 * Created by kendu on 2017-05-04.
 */
public interface ICollisionObservable {

    void addCollisionListener(ICollisionObserver observer);
    void removeCollisionListener(ICollisionObserver observer);

}
