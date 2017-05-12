package View.ObjectView;

import Model.GameObjects.Ball;
import Model.GameObjects.IBall;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

/**
 * Created by DukeA on 2017-04-28.
 */
public class BallView implements IViews {

    private IBall ball;
    private ShapeRenderer shapeRenderer;
    private int WIDTH;
    private int HEIGHT;


    public BallView(int WIDTH, int HEIGHT, ShapeRenderer renderer) {
        this.WIDTH = WIDTH;
        this.HEIGHT = HEIGHT;
        this.shapeRenderer = renderer;
        ball = new Ball(WIDTH/2 - 250, HEIGHT / 2+20, 30f, 1, 100);
    }
    @Override
    public void render(float delta) {
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(Color.RED);
        shapeRenderer.ellipse(ball.getball().getX()
                , ball.getball().getY()
                ,ball.getball().getRadius() *((WIDTH/4)/(HEIGHT/4))
                ,ball.getball().getRadius()*((WIDTH/4)/(HEIGHT/4)));
        shapeRenderer.end();

    }
    @Override
    public void reSize(int width, int height) {

    }
    @Override
    public void dispose() {
        shapeRenderer.dispose();
    }

    @Override
    public void update() {

    }
}
