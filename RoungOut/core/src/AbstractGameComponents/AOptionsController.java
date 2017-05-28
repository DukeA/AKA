package AbstractGameComponents;

import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.scenes.scene2d.ui.CheckBox;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import java.util.ArrayList;

/**
 * @author DukeA
 * Created on 2017-05-25.
 */
public abstract class AOptionsController  extends ClickListener implements InputProcessor{
    /**
     *
     * @param WIDTH Width of the screen
     * @param HEIGHT Height od the screen
     * @param box the box that was clicked
     * @param game the game we are currently using
     */
    public abstract void boxClicked(int WIDTH, int HEIGHT, CheckBox box, AGame game);

    /**
     *
     * @param box The mox that corresponds to the mute mox
     * @param game the game we are currently using
     * @return ABoolean that toggles the sound option
     */
    public abstract boolean muteBoxClicked(CheckBox box, AGame game);

    /**
     * If we press escape in the options we go to the main menu
     *
     * @param WIDTH Width of the screen
     * @param HEIGHT Height od the screen
     * @param game the game we are currently using
     */
    public abstract void escapeClicked(int WIDTH,int HEIGHT ,AGame game);

}
