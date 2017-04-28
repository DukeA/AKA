package Controller;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;

import java.awt.event.ActionListener;

/**
 * Created by Alex on 2017-04-28.
 */
public class PlayerController implements IPlayerController {

    public char KeyPressed(char x){
        return x;
    }

    boolean W = Gdx.input.isKeyPressed(Input.Keys.W);
    boolean S = Gdx.input.isKeyPressed(Input.Keys.S);
}
