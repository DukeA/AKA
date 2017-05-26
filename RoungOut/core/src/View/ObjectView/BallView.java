package View.ObjectView;

import IViews.IViews;
import Model.GameObjects.Ball;
import Model.GameObjects.Board;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

/**
 * Created by DukeA on 2017-04-28.
 */
public class BallView implements IViews {

    private Board ball;
    private ShapeRenderer shapeRenderer;
    private int WIDTH;
    private int HEIGHT;


    public BallView(int WIDTH, int HEIGHT, ShapeRenderer renderer,Board board) {
        this.WIDTH = WIDTH;
        this.HEIGHT = HEIGHT;
        this.shapeRenderer = renderer;
        ball = board;

    }

    @Override
    public void render(float delta) {
        for (Ball ball: ball.getBalls()) {
            update(delta);
            shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
            shapeRenderer.setColor(Color.RED);
            shapeRenderer.ellipse(
                    ball.getX() - ball.getRadius(),
                    ball.getY() - ball.getRadius(),
                    ball.getRadius() * 2,
                    ball.getRadius() * 2);
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
