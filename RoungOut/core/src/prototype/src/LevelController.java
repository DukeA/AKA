package prototype.src;

import Objects.Board;
import Objects.IBoard;
import Objects.Pad;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;

import javax.swing.*;


/**
 * Created by DukeA on 2017-03-31.
 */

public class LevelController {

    private final int WIDTH = Gdx.graphics.getWidth() / 2;
    private final int HEIGHT = Gdx.graphics.getHeight() / 2;

    private final int PADHEIGHT = 15;
    private final int PADWIDTH = 15;
    private final int PADXPOS = 25;
    private final int PADYPOS = 25;


    private ShapeRenderer renderPad;
    private ShapeRenderer renderPad2;
    private Pad nPad;
    private Pad nPad2;

    public LevelController() {
        init();
    }

    private void init() {
        TestPad();
    }

    private void TestPad() {


    }

    public void update(float deltatime) {

    }
}



