package AbstractGameComponents;

import com.badlogic.gdx.scenes.scene2d.ui.CheckBox;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

/**
 * Created by DukeA on 2017-05-25.
 */
public abstract class AOptionsController  extends ClickListener{
    public abstract void boxClicked(int WIDTH, int HEIGHT, CheckBox box, AGame game);
    public abstract boolean muteBoxClicked(CheckBox box, AGame game);
    public abstract void escapeClicked(int WIDTH,int HEIGHT ,AGame game);

}
