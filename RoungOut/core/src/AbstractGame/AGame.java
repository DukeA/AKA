package AbstractGame;

import AbstractController.AGameController;
import AbstractController.AMenuController;
import AbstractController.AOptionsController;
import Model.GameObjects.Board;
import com.badlogic.gdx.Game;

/**
 * Created by Alex on 2017-05-20.
 */
public abstract class AGame extends Game {
    public abstract Board getBoard ();
    public abstract AGameController getGameController();
    public abstract AMenuController getMenuController();
    public abstract AOptionsController getOptionsController();
}
