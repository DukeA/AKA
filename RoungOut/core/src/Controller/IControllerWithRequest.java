package Controller;

import Controller.IController;
import com.badlogic.gdx.InputProcessor;

/**
 * Created by Deltagare on 2017-05-18.
 */
public interface IControllerWithRequest extends IController {
    void atRequest();
}
