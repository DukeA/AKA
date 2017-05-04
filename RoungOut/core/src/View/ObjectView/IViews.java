package View.ObjectView;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

/**
 * Created by DukeA on 2017-05-04.
 */
public interface IViews {
    BallView createBall(int xPos, int yPos, SpriteBatch batch, ShapeRenderer renderer);
    PadView createPad(int xPos,int yPos,SpriteBatch batch,ShapeRenderer renderer);
}
