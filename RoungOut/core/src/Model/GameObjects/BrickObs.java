package Model.GameObjects;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by DukeA on 2017-05-12.
 */
public class BrickObs {

    private List<Observer> observers = new ArrayList<Observer>();
    private enum State {
        Destroyed,Alive;
    }

    public void Attach (Observer observer) {
        observers.add(observer);
    }
    public void notifyAllObserver() {
        for (Observer observer: observers) {
            observer.update();
        }
    }
}
