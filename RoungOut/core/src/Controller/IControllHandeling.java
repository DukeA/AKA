package Controller;

import Controller.EnumIndexes;
import com.badlogic.gdx.InputProcessor;

/**
 * @author Alex
 * Created on 2017-05-12.
 */
public interface IControllHandeling{
    /**
     * Used to change the input processor to the current instance
     */
    void changeInputProcessor();

    /**
     * Getter, gets the enum for the "menu" (it's the same as the controllers since the corresponds to a "menu-view", see EnumIndexes)
     * @return an enum that defines the controller's type
     */
    EnumIndexes getTypeOfMenu();

}

