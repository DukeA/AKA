package AbstractController;

import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

/**
 * Created by Alex on 2017-05-25.
 */
public abstract class AGameController extends ClickListener implements InputProcessor{
    public abstract void atRequest();
}
