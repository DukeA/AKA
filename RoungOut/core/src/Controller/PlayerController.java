package Controller;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;


/**
 * Created by Alex on 2017-04-28.
 */
public class PlayerController implements IPlayerController, InputProcessor{

    //Add list of models that want this controller as input
    //Add list of views that want this controller as input

    private char latestKey= ' ';
    //KeyPressed returns a char, its used in the IPlayerController to signal inputs to other parts of the program
    @Override
    public char latestKeyPressed(){
        return latestKey;
    }

    @Override
    public boolean addListener() {
        return false;
    }

    @Override
    public boolean removeListener() {
        return false;
    }

    @Override
    public boolean keyDown(int keycode) {
        /**
         *  This will be called when a key is pressed down (keyboard) by libgdx's InputProcessor
         */
        if (keycode == Input.Keys.A){

            //Interface for moving Player 1 left

            latestKey= 'A';
        }

        if (keycode == Input.Keys.D){

            //Interface for moving Player 1 right

            latestKey= 'D';
        }

        if (keycode == Input.Keys.DPAD_LEFT){
            //V since upp arrow char doesn't exist
            //Interface for moving Player 2 left

            latestKey= 'V';
        }

        if (keycode == Input.Keys.DPAD_RIGHT){
           //H since upp arrow char doesn't exist
            //Interface for moving Player 2 right

            latestKey= 'H';
        }

        /**
         *  P1:WASD          P2:UNVH (Upp, Ner, Vnster, Hger)
         *      W                   U
         *  A   S   D           V   N   H
         *
         *  It would be much simpler if the return value of the interfaced could be an int and
         *  simply import the "com.badlogic.gdx.Input" library to reach the Inputs.Keys field
         */
        return true;
    }

    //We don't need these inputs
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

    public PlayerController() {
        Gdx.input.setInputProcessor(this);
        //Makes it so LibGdx sends calls to this controller
        //(Adds the created PlayerController as a listener for LibGdx)
    }
}
