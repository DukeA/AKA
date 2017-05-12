package View.ObjectView;

import Model.GameObjects.Ball;
import Model.GameObjects.Board;
import Model.GameObjects.IBall;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

import java.util.ArrayList;

/**
 * Created by DukeA on 2017-04-28.
 */
public class BallView implements IViews {

    private Board ball;
    private ShapeRenderer shapeRenderer;
    private int WIDTH;
    private int HEIGHT;


    public BallView(int WIDTH, int HEIGHT, ShapeRenderer renderer) {
        this.WIDTH = WIDTH;
        this.HEIGHT = HEIGHT;
        this.shapeRenderer = renderer;
        ball = new Board(WIDTH, HEIGHT);

    }

    @Override
    public void render(float delta) {
        for (Ball ball: ball.getBalls()) {
            update(delta);
            shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
            shapeRenderer.setColor(Color.RED);
            shapeRenderer.ellipse(ball.getX()
                    , ball.getY()
                    , ball.getRadius() * ((WIDTH / 4) / (HEIGHT / 4))
                    , ball.getRadius() * ((WIDTH / 4) / (HEIGHT / 4)));
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
