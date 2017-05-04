package Model.Collsion;

/**
 * Created by kendu on 2017-05-04.
 */
public interface ICollisionObserver {
    void onCollision(ICollisionObservable observable, double distance, double surfaceAngle);
}
