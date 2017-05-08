package Controller;

import com.badlogic.gdx.InputProcessor;

import java.util.ArrayList;

/**
 * Created by Alex on 2017-05-08.
 */
public class ControllerHandler implements InputProcessor {


    private static ArrayList<ControllerHandler> controllerList = new ArrayList<ControllerHandler>();

    public static ControllerHandler getControllerAtIndex (int index) {
        return controllerList.get(index);
    }

    public static void setControllerAtIndex (int index, ControllerHandler controller){
        controllerList.add(index, controller);
    }



    //start
    @Override
    public boolean keyDown(int keycode) {
        return false;
    }
    @Override
    public boolean keyUp(int keycode) {
        return false;
    }
    @Override
    public boolean keyTyped(char character) {
        return false;
    }
    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        return false;
    }
    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }
    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }
    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }
    @Override
    public boolean scrolled(int amount) {
        return false;
    }
    //end

    ControllerHandler() {}
}
