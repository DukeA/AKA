package Model.GameObjects;

import Model.Collsion.ICollisionObservable;
import Model.Collsion.ICollisionObserver;
import Model.GameObjects.Physics.Body;
import Model.GameObjects.Physics.CircleBody;

import java.util.HashSet;
import java.util.Set;

/**
 * @author Ken Bäcklund
 */
public class Ball implements IModel, Body, IBodyStateChangeObservable, ICollisionObserver {

    private CircleBody body;
    private Set<IBodyStateChangeObserver> observerSet;

    public Ball(double xPos, double yPos, double radius, double angle, double speed) {
        body = new CircleBody(xPos, yPos, radius, angle, speed);
        observerSet = new HashSet<IBodyStateChangeObserver>();
    }

    public Ball(double xPos, double yPos, double radius) {
        this(xPos, yPos, radius, 0f, 0f);
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

    public double getRadius() {
        return body.getRadius();
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

    public void setRadius(double radius) {
        body.setRadius(radius);
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
        notifyObservers();
        // Notify *before* ball moves. This is necessary for a collision checker to catch high speed objects.
        body.move();
    }

    @Override
    public void move(double distance) {
        notifyObservers();
        // Notify *before* ball moves. This is necessary for a collision checker to catch high speed objects.
        body.move();
    }

    public void keyWasPressed(int key) {
        // Nothing to do here
    }

    // Observer methods ///////////////////////////////////////////////////////
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

    @Override
    public void onCollision(ICollisionObservable observable, double distance, double surfaceAngle) {
        double relAngle = (body.getAngle() - surfaceAngle + 2f*Math.PI) % 2f*Math.PI;
        if (relAngle > (3f/2f*Math.PI) || relAngle < Math.PI) {
            body.setAngle(Math.PI - relAngle);  // Bounce
            body.move(2f*distance);     // Adjust for that ball will move directly on return.
        }
    }
}
