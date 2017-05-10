package View.ObjectView;

import Model.GameObjects.Ball;
import Model.GameObjects.IBall;
import Model.GameObjects.Physics.CircleBody;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.FitViewport;

/**
 * Created by DukeA on 2017-04-28.
 */
public class BallView implements  IBall,IViews {

    private Ball ball;
    private SpriteBatch batch;
    private ShapeRenderer shapeRenderer;
    private int WIDTH;
    private int HEIGHT;
    private float xBall;
    private float yBall;

    public BallView(int WIDTH, int HEIGHT, ShapeRenderer renderer) {
        this.WIDTH = WIDTH;
        this.HEIGHT = HEIGHT;
        this.shapeRenderer = renderer;
        ball = getball();
    }
    @Override
    public void render(float delta) {
        this.update(delta);
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(Color.RED);
        shapeRenderer.ellipse(ball.getX()
                , ball.getY()
                ,ball.getRadius() *((WIDTH/4)/(HEIGHT/4))
                ,ball.getRadius()*((WIDTH/4)/(HEIGHT/4)));
        shapeRenderer.end();

    }

    @Override
    public void update(float delta) {
        ball.move(delta);
    }
    @Override
    public void reSize(int width, int height) {

    }
    @Override
    public void dispose() {
        shapeRenderer.dispose();
    }
    @Override
    public Ball getball() {
        return new Ball(WIDTH / 2+30, HEIGHT / 2-30, 30f, 0, 0);
    }
}
