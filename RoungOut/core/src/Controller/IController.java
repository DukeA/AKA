package Controller;

import com.badlogic.gdx.InputProcessor;

/**
 * Created by Alex on 2017-05-12.
 */
public interface IController extends InputProcessor{
    void changeInputProcessor();
    EnumIndexes getTypeOfMenu();

}

