package AbstractGameComponents;

import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

/**
 * Created by DukeA on 2017-05-24.
 */
public abstract class AMenuController  extends ClickListener {
    public abstract void playButtonIsPressed(int Width, int Height, AGame game);
    public abstract void optionsButtonIsPressed(int Width, int Height, AGame game);
    public abstract void exitButtonIsPressed();
}