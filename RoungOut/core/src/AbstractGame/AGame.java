package AbstractGame;

import Controller.GameController;
import Controller.IController;
import Controller.MenuController;
import Controller.OptionsController;
import Model.GameObjects.Board;
import com.badlogic.gdx.Game;

/**
 * Created by Alex on 2017-05-20.
 */
public abstract class AGame extends Game {
    public abstract Board getBoard ();
    public abstract GameController getGameController();
    public abstract OptionsController getOptionsController();
    public abstract MenuController getMenuController ();
}
