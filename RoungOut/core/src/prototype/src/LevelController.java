package prototype.src;

import Objects.Board;
import Objects.IBoard;
import Objects.Pad;
import com.badlogic.gdx.Gdx;
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


    private ShapeRenderer renderer;

    public LevelController() {
        init();
    }

    private void init() {
        TestBoard();
        TestPad();
    }

    private void TestPad() {
        Pad nPad = new Pad(PADHEIGHT, PADWIDTH, PADXPOS, PADYPOS);
        renderer = new ShapeRenderer();
        renderer.begin(ShapeRenderer.ShapeType.Filled);
        renderer.setColor(Color.RED);
        renderer.rect(PADXPOS, PADYPOS, PADWIDTH, PADHEIGHT);
        renderer.end();

        Pad nPad2 = new Pad(PADHEIGHT, PADWIDTH, PADXPOS, PADYPOS);
        renderer = new ShapeRenderer();
        renderer.begin(ShapeRenderer.ShapeType.Filled);
        renderer.setColor(Color.BLUE);
        renderer.rect(PADXPOS + 20, PADYPOS + 20, PADWIDTH, PADHEIGHT);
        renderer.end();

    }

    private void TestBoard() {

        IBoard board = new Board(WIDTH, HEIGHT);
        renderer = new ShapeRenderer();
        renderer.begin(ShapeRenderer.ShapeType.Line);
        Gdx.gl20.glLineWidth(5);
        renderer.setColor(Color.BLACK);
        renderer.circle(board.getxPos(), board.getYPos(), board.getRadius());
        renderer.end();
    }

    public void update(float deltatime) {

    }
}
