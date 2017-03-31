package prototype.src;

import Objects.Board;
import Objects.IBoard;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;


/**
 * Created by DukeA on 2017-03-31.
 */

public class LevelController {

    private final int WIDTH = Gdx.graphics.getWidth();
    private final int HEIGHT = Gdx.graphics.getHeight();


    private ShapeRenderer renderer;

    public LevelController() {
        init();
    }
    private void init() {
        TestBoard();
    }
    public void TestBoard() {
        IBoard board = new Board(WIDTH,HEIGHT);
        renderer = new ShapeRenderer();
        renderer.begin(ShapeRenderer.ShapeType.Line);
        renderer.rectLine(board.getxPos(),board.getYPos(),
                board.getxPos()+1,board.getYPos()+1,30);
        renderer.setColor(Color.BLACK);
        renderer.circle(board.getxPos(),board.getYPos(),board.getRadius());
        renderer.end();
    }

    public void update(float deltatime) {

    }
}
