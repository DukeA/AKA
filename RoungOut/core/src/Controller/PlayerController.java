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
    public char KeyPressed(char x){
        return x;
    }

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

    boolean W = Gdx.input.isKeyPressed(Input.Keys.W);
    boolean S = Gdx.input.isKeyPressed(Input.Keys.S);
}
