package Model.GameObjects;

import java.util.Set;

/**
 * Created by kendu on 2017-05-09.
 */
public interface IBrick {
    public float getX();
    public float getY();
    public float getWidth();
    public float getHeight();
    public Brick.BrickType getBrickType();
    public void markDestroyed();
    public boolean isDestroyed();
}
