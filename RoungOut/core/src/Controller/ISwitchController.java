package Controller;

import com.badlogic.gdx.InputProcessor;

import java.util.ArrayList;

/**
 * Created by Alex on 2017-05-09.
 */
public interface ISwitchController extends InputProcessor{
    void updateControllerList (ArrayList<ISwitchController> newList );
    void setCurrentIP(int index);
}
