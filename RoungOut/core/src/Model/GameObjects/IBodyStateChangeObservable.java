package Model.GameObjects;

/**
 * Makes a Body instance observable.
 * Should trigger by any changes such as position, speed or directional changes.
 *
 * Observers are notified using the following method:
 *     void onBodyStateChange(Body body)
 *
 * @author Ken Bäcklund
 */
public interface IBodyStateChangeObservable {
    void addListener(IBodyStateChangeObserver observer);
    void removeListener(IBodyStateChangeObserver observer);
}
