package AbstractGameComponents;

import Model.GameObjects.Board;
import com.badlogic.gdx.Game;

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
}
