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


    private final int WIDTH = Gdx.graphics.getWidth();
    private final int HEIGHT = Gdx.graphics.getHeight();

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
        nPad = new Pad(PADHEIGHT, PADWIDTH, PADXPOS, PADYPOS);
        renderPad = new ShapeRenderer();
        renderPad.begin(ShapeRenderer.ShapeType.Filled);
        renderPad.setColor(Color.RED);
        renderPad.rect(PADXPOS, PADYPOS, PADWIDTH, PADHEIGHT);
        renderPad.end();

        nPad2 = new Pad(PADHEIGHT, PADWIDTH, PADXPOS, PADYPOS);
        renderPad2 = new ShapeRenderer();
        renderPad2.begin(ShapeRenderer.ShapeType.Filled);
        renderPad2.setColor(Color.BLUE);
        renderPad2.rect(PADXPOS + 20, PADYPOS + 20, PADWIDTH, PADHEIGHT);
        renderPad2.end();

    }
    public int getXPos(Pad p)  {
        return
    }

    public void update(float deltatime) {
        if(Gdx.input.isKeyPressed(Input.Keys.W)) {
            if(nPad.getPadXPos()) {
                renderPad.translate(0, 1,0);
            }
            else if() {

            }
        }
        if(Gdx.input.isKeyPressed(Input.Keys.S)) {
            if(nPad.getPadXPos()) {
                renderPad.translate(0,-1,0);
            }
            else if() {

            }
        }
        if(Gdx.input.isKeyPressed(Input.Keys.DPAD_DOWN)) {
            if(nPad2.getPadXPos()) {
                renderPad2.translate();
            }
            else if() {

            }
        }
        if(Gdx.input.isKeyPressed(Input.Keys.DPAD_UP)) {
            if(nPad2.getPadXPos()) {
                renderPad2.translate();
            }
            else if() {

            }
        }
    }
}
