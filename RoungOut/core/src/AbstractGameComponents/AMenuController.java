package AbstractGameComponents;

import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

/**
 * @author DukeA
 * Created on 2017-05-24.
 */
public abstract class AMenuController  extends ClickListener implements InputProcessor{
    /**
     *
     * @param Width Width of the screen
     * @param Height Height of the screen
     * @param game the game we are currently using
     */
    public abstract void playButtonIsPressed(int Width, int Height, AGame game);

    /**
     *
     *  @param Width Width of the screen
     * @param Height Height of the screen
     * @param game the game we are currently using
     */
    public abstract void optionsButtonIsPressed(int Width, int Height, AGame game);

    /**
     * Exits the applications i the implementation of the method
     */
    public abstract void exitButtonIsPressed();
}