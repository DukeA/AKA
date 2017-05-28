package AbstractGameComponents;

import Model.GameObjects.Board;
import com.badlogic.gdx.Game;
import prototype.src.desktop.Roungout;

import java.util.ArrayList;

/**
 *  @author Alex
 *  Created on 2017-05-20.
 */
public abstract class AGame extends Game {
    /**
     * @return Returns a board
     */
    public abstract Board getBoard ();

    /**
     * Gets a updated board with the new width and height
     * @return Returns a board
     */
    public abstract Board getUpdateBoard(int Width,int HEIGHT);

    /**
     *
     * @return Returns an abstract gameController
     */
    public abstract AGameController getGameController();

    /**
     *
     * @return Returns an abstract menuController
     */
    public abstract AMenuController getMenuController();

    /**
     *
     * @return Returns an abstract optionsController
     */
    public abstract AOptionsController getOptionsController();
    public abstract ArrayList<Integer> setSize(int Width, int Height);
}
