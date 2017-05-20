package Model.GameObjects;

import java.util.ArrayList;
import java.util.Set;

/**
 * Created by Adam on 2017-03-30.
 * Update by Ken on 2017-05-12
 *
 */
public interface IBoard {
    public float getXPos();
    public float getYPos();
    public float getRadius();

    public Set<Ball> getBalls();
    public Set<Brick> getBricks();
    public ArrayList<Player> getPlayers();

    public void update( float delta);

}
