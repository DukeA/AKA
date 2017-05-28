package View.ObjectView;

import IViews.IViews;
import Model.GameObjects.Board;
import Model.GameObjects.Brick;
import Model.GameObjects.BrickSlowDownBall;
import Model.GameObjects.BrickSpeedUpBall;
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


    /**
     * Constructor
     * @param Width Width of the screen
     * @param Height Height of the screen
     * @param renderer the Renderer we want to use to render with (par of framework)
     * @param board The board we are using
     */
    public BrickView(int Width, int Height, ShapeRenderer renderer,Board board) {
        this.WIDTH = Width;
        this.HEIGHT = Height;
        this.shapeRenderer = renderer;
        this.board = board;

    }

    /**
     * Used by BoardView to render the visual in this view
     * @param delta time between frames, comes from interface
     */
    public void render(float delta) {
        for (Brick brick : board.getBricks()) {

            if (!brick.isDestroyed()) {
                shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
                if (brick.getBrickType() == Brick.BrickType.SPEED_UP_BALL) {
                    // BrickSpeedUp
                    shapeRenderer.setColor(Color.GREEN);
                }
                else if (brick.getBrickType() == Brick.BrickType.SLOW_DOWN_BALL) {
                    // BrickSlowDown
                    shapeRenderer.setColor(Color.RED);
                }
                else {
                    // Regular Brick
                    shapeRenderer.setColor(Color.DARK_GRAY);
                }

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
    public void reSize(int width, int height) {}

    /**
     * Disposes the shape renderer
     */
    @Override
    public void dispose() {
        shapeRenderer.dispose();
    }


    @Override
    public void update(float delta) {
    }
}
