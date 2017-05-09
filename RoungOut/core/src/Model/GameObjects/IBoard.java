package Model.GameObjects;

import java.util.Set;

/**
 * Created by Adam on 2017-03-30.
 * Updated by Ken on 2017-05-09.
 */
public interface IBoard {
    public float getXPos();
    public float getYPos();
    public float getRadius();
    public Set<Ball> getBalls();
    public Set<Brick> getBricks();
    public Set<Pad> getPads();
}
