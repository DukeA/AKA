package Controller;

import View.IView;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;

import java.util.ArrayList;

/**
 * Created by Deltagare on 2017-05-13.
 */
public class MenuController implements IController {


    //List that contains the views that this controller interacts with
    private ArrayList<IView> viewSubscribers = new ArrayList<IView>();

    //The handler that handles the switching of input processor
    private IHandler handler;

    //the enum that decides the typ of menu this is
    EnumIndexes typeOfMenu;


    private String latestKey = " ";//init with blank
    public String latestKeyPressed()
    {
        //Used for debug
        return latestKey;
    }

    @Override
    public boolean keyDown(int keycode) {
        /**
         *  This will be called when a key is pressed down (keyboard) by libgdx's InputProcessor
         */
        latestKey = Input.Keys.toString(keycode);

        if (keycode== Input.Keys.ESCAPE){
            handler.callSetNewInput(EnumIndexes.PLAYER_CONTROLLER);
            //IF ESC -> Go back to the game
            updateAllViews();
        }

        return true;
    }

    //Helper method, code reuse
    private void updateAllViews() {
        //Call update to all views
        for (IView view : viewSubscribers) {
            view.update();
        }
    }

    @Override
    public void changeInputProcessor() {
        Gdx.input.setInputProcessor(this);
    }

    @Override
    public EnumIndexes getTypeOfMenu(){ return typeOfMenu;}

    //We don't need these inputs
    @Override
    public boolean keyUp(int keycode) {
        return false;
    }
    @Override
    public boolean keyTyped(char character) { return false; }
    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) { return false; }
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
    //End of these inputs


    public MenuController(ArrayList<IView> viewSubscribers, IHandler handler) {
        this.viewSubscribers = viewSubscribers;

        this.handler = handler;


    }
}
