package Controller;

import Controller.EnumIndexes;
import com.badlogic.gdx.InputProcessor;

/**
 * Created by Alex on 2017-05-12.
 */
public interface IControllHandeling extends InputProcessor{
    void changeInputProcessor();
    EnumIndexes getTypeOfMenu();

}

