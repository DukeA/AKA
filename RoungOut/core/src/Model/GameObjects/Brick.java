package Model.GameObjects;

import Model.Collsion.ICollisionObservable;
import Model.Collsion.ICollisionObserver;
import Model.GameObjects.IModel;
import Model.GameObjects.Physics.*;

import java.util.Set;

/**
 * @author Ken Bäcklund
 */
public class Brick implements IModel, Body, IBodyStateChangeObservable, ICollisionObserver {

    private RectangleBody body;
    private Set<IBodyStateChangeObserver> observerSet;

    public Brick(double xPos, double yPos, double width, double height, double angle, double speed) {
        body = new RectangleBody(xPos, yPos, width, height, angle, speed);
    }

    public Brick(double xPos, double yPos, double width, double height) {
        body = new RectangleBody(xPos, yPos, width, height, 0f, 0f);
    }

    public double getX() {
        return body.getX();
    }

    public double getY() {
        return body.getY();
    }

    public double getAngle() {
        return body.getAngle();
    }

    public double getSpeed() {
        return body.getSpeed();
    }

    public void setX(double xPos) {
        body.setX(xPos);
        notifyObservers();
    }

    public void setY(double yPos) {
        body.setY(yPos);
        notifyObservers();
    }

    public void setPosition(double xPos, double yPos) {
        body.setPosition(xPos, yPos);
        notifyObservers();
    }

    public void setAngle(double radians) {
        body.setAngle(radians);
        notifyObservers();
    }

    public void setSpeed(double speed) {
        body.setSpeed(speed);
        notifyObservers();
    }

    public void setMaxSpeed(double maxSpeed) {
        body.setMaxSpeed(maxSpeed);
        notifyObservers();
    }

    public double distance(Body otherBody) {
        return body.distance(otherBody);
    }

    public double distance(double xPos, double yPos) {
        return body.distance(xPos, yPos);
    }

    public void move() {
        notifyObservers();      // Done before a move()
        body.move();
    }

    public void move(double distance) {
        notifyObservers();      // Done before a move()
        body.move(distance);
    }

    public void keyWasPressed(int key) {
        // Nothing to do here.
    }

    // Observer classes ///////////////////////////////////////////////////////
    public void onCollision(ICollisionObservable observable, double distance, double surfaceAngle) {
        // Brick was hit by (observable).
        // TODO: Remove brick.
    }

    public void addListener(IBodyStateChangeObserver observer) {
        if (observer != null) {
            observerSet.add(observer);
        }
    }

    public void removeListener(IBodyStateChangeObserver observer) {
        if (observer != null) {
            observerSet.remove(observer);
        }
    }

    private void notifyObservers() {
        for (IBodyStateChangeObserver observer : observerSet) {
            if (observer != null) {
                observer.onStateChange(this);
            }
        }
    }

}
