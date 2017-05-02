package Controller;

import View.IView;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;

import java.util.ArrayList;


/**
 * Created by Alex on 2017-04-28.
 */
public class PlayerController implements IPlayerController, InputProcessor{
    //The list will only contain unique subscribers and this is guaranteed by the
    //addListener and removeListener methods
    private ArrayList<IView> viewSubscribers = new ArrayList<IView>();


    //Add list of models that want this controller as input

    private char latestKey= ' ';//init with blank
    @Override
    public char latestKeyPressed()
    {
        //Used for debug
        return latestKey;
    }

    @Override
    public boolean addListener(IView view) {

       if(!viewSubscribers.contains(view)){ //If we don't have a the view, add it
           viewSubscribers.add(view);
           return true;
       }

        return false;
    }

    @Override
    public boolean removeListener(IView view) {

        if(viewSubscribers.contains(view)){ //If we do have the view, remove it
            viewSubscribers.remove(view);
            return true;
        }
        return false;
    }

    @Override
    public boolean keyDown(int keycode) {
        /**
         *  This will be called when a key is pressed down (keyboard) by libgdx's InputProcessor
         */
        if (keycode == Input.Keys.A){
            latestKey= 'A'; //debug
            callSubscribersWith(keycode);
        }

        if (keycode == Input.Keys.D){
            latestKey= 'D';//debug
            callSubscribersWith(keycode);
        }

        if (keycode == Input.Keys.DPAD_LEFT){
            latestKey= 'V'; //debug
            callSubscribersWith(keycode);
        }

        if (keycode == Input.Keys.DPAD_RIGHT){
            latestKey= 'H'; //debug
            callSubscribersWith(keycode);
        }

        /**
         *  P1:WASD          P2:UNVH (Upp, Ner, Vnster, Hger)
         *      W                   U
         *  A   S   D           V   N   H
         *
         *  It would be much simpler if the return value of the interfaced could be an int and
         *  simply import the "com.badlogic.gdx.Input" library to reach the Inputs.Keys field
         *  UPDATE: Decided to send ints instead of chars since that was simpler, char still stored for debug
         *
         */
        return true;
    }

    //Helper method, code reuse
    private void callSubscribersWith(int i){
        //Call all subscribers
        for (IView view: viewSubscribers){
            view.keyWasPressed(i);
        }
    }

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

    public PlayerController() {
        Gdx.input.setInputProcessor(this);
        //Makes it so LibGdx sends calls to this controller
        //(Adds the created PlayerController as a listener for LibGdx)
    }
}
