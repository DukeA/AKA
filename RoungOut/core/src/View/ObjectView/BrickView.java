package View.ObjectView;

import Model.GameObjects.Ball;
import Model.GameObjects.Board;
import Model.GameObjects.Brick;
import Model.GameObjects.Physics.RectangleBody;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by DukeA on 2017-05-06.
 */
public class BrickView implements  IViews {

    private Board board;
    private int WIDTH;
    private int HEIGHT;
    private ShapeRenderer shapeRenderer;




    public BrickView(int Width, int Height, ShapeRenderer renderer,Board board) {
        this.WIDTH = Width;
        this.HEIGHT = Height;
        this.shapeRenderer = renderer;
        this.board = board;

    }

    public void render(float delta) {
        for (Brick brick : board.getBricks()) {

            shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
            shapeRenderer.setColor(Color.DARK_GRAY);
            shapeRenderer.rect((float)brick.getX(),(float)brick.getY(),
                    30f,30f);
            shapeRenderer.end();
        }
    }


    @Override
    public void reSize(int width, int height) {

    }

    @Override
    public void dispose() {
        shapeRenderer.dispose();

    }


    @Override
    public void update(float delta) {

    }
}
