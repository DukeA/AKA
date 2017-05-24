package AbstractController;

import Controller.IController;
import Controller.MenuController;
import Controller.OptionsController;

/**
 * Created by DukeA on 2017-05-24.
 */
public  abstract class AController {
    public abstract MenuController getMenuController();
    public abstract OptionsController getOptionsController();
}
