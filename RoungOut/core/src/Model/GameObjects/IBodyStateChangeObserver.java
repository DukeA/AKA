package Model.GameObjects;

import Model.GameObjects.Physics.Body;

/**
 * Listen for changes in a Body instance such as movement, speed, and directional change.
 *
 * @author Ken Bäcklund
 */
public interface IBodyStateChangeObserver {

    /**
     * Callback method to notify observer that a body as changed.
     * @param body - the Body instance that changed.
     */
    void onBodyStateChange(Body body);
}
