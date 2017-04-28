package Controller;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;


/**
 * Created by Alex on 2017-04-28.
 */
public class PlayerController implements IPlayerController, InputProcessor{

    //KeyPressed returns a char, its used in the IPlayerController to signal inputs to other parts of the program
    @Override
    public char keyWasPressed(char x){
        return x;
    }

    @Override
    public boolean keyDown(int keycode) {
        if (keycode == Input.Keys.A){
            keyWasPressed('A');
        }

        if (keycode == Input.Keys.D){
            keyWasPressed('D');
        }

        if (keycode == Input.Keys.DPAD_LEFT){
            keyWasPressed('V'); //U since upp arrow char doesn't exist
        }

        if (keycode == Input.Keys.DPAD_RIGHT){
            keyWasPressed('H');  //N since upp arrow char doesn't exist
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

    boolean W = Gdx.input.isKeyPressed(Input.Keys.W);
    boolean S = Gdx.input.isKeyPressed(Input.Keys.S);
}
