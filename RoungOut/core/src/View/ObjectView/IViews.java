package View.ObjectView;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.Stage;

/**
 * Created by DukeA on 2017-05-04.
 */
public interface IViews {


    public void render(float delta);
    public void reSize(int width,int height);
    public void dispose();
    public void update(float delta);
}
