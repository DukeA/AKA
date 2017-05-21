package Controller;

import com.badlogic.gdx.InputProcessor;

/**
 * Created by Alex on 2017-05-12.
 */
public interface IControllerHandeling extends InputProcessor{
    void changeInputProcessor();
    EnumIndexes getTypeOfMenu();

}

