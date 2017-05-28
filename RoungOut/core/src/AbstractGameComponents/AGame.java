package AbstractGameComponents;

import Model.GameObjects.Board;
import com.badlogic.gdx.Game;
import prototype.src.desktop.Roungout;

/**
 * Created by Alex on 2017-05-20.
 */
public abstract class AGame extends Game {
    public abstract Board getBoard ();
    public abstract AGameController getGameController();
    public abstract AMenuController getMenuController();
    public abstract AOptionsController getOptionsController();
    public abstract Roungout getRoungout(int Width, int Height);
}
