package View.ObjectView;

import IViews.IViews;
import Model.GameObjects.Board;
import Model.GameObjects.Brick;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

/**
 * Created by DukeA on 2017-05-06.
 */
public class BrickView implements IViews {

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

            if (brick.isDestroyed()) {
                /*
                shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
                shapeRenderer.setColor(Color.LIGHT_GRAY);
                shapeRenderer.rect(
                        brick.getX() - brick.getWidth()/2,
                        brick.getY() - brick.getHeight()/2,
                        brick.getWidth(),
                        brick.getHeight());
                shapeRenderer.end();
                */
            }
            else {
                shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
                shapeRenderer.setColor(Color.DARK_GRAY);
                shapeRenderer.rect(
                        brick.getX() - brick.getWidth()/2,
                        brick.getY() - brick.getHeight()/2,
                        brick.getWidth(),
                        brick.getHeight());
                shapeRenderer.end();
            }

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
