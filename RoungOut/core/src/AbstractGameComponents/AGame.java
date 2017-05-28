package AbstractGameComponents;

import Model.GameObjects.Board;
import com.badlogic.gdx.Game;
import prototype.src.desktop.Roungout;

import java.util.ArrayList;

/**
 * Created by Alex on 2017-05-20.
 */
public abstract class AGame extends Game {
    public abstract Board getUpdateBoard(int Width,int HEIGHT);
    public abstract AGameController getGameController();
    public abstract AMenuController getMenuController();
    public abstract AOptionsController getOptionsController();
    public abstract ArrayList<Integer> setSize(int Width, int Height);
}
