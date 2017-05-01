package Model.Collsion;

import Model.GameObjects.Body;
import Model.ViewObjects.IBoard;

/**
 * @author Ken Bäcklund
 */
public class Collision {

    public boolean isCollision(Body someBody, Body anyBody) {
        return (someBody.distance(anyBody) <= 0.0001f);
    }

}
