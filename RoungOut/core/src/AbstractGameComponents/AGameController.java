package AbstractGameComponents;

import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

/**
 * @author Alex
 * Created on 2017-05-25.
 */
public abstract class AGameController extends ClickListener implements InputProcessor{
    /**
     * This is a method that is called when the boardView wants to update the model with the
     * key inputs from a player, this is because the boardView updates the model (the View has this responsibility
     * because of the framework)
     */
    public abstract void atRequest();
}
