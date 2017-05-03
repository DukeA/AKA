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
public class BallView implements Screen, IBall {

    private Ball ball;
    private Stage stage;
    private SpriteBatch batch;
    private ShapeRenderer shapeRenderer;
    private int WIDTH;
    private int HEIGHT;
    OrthographicCamera camera;

    public BallView(int WIDTH, int HEIGHT) {
        this.WIDTH = WIDTH;
        this.HEIGHT = HEIGHT;
        ball = createBall(WIDTH/2,HEIGHT/2);
        this.stage = new Stage(
                new FitViewport(WIDTH, HEIGHT));

    }

    @Override
    public void show() {


    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(1f, 1f, 1f, 1f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);
        camera = new OrthographicCamera(WIDTH,HEIGHT);
        camera.update();
        batch = new SpriteBatch();
        shapeRenderer = new ShapeRenderer();
        batch.begin();
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(Color.RED);
        shapeRenderer.ellipse(
                (float) (ball.getX())
                , (float) (ball.getY())
                , (float) ((ball.getRadius() * ball.getY() / ball.getX()))
                , (float) (ball.getRadius() * ball.getY() / ball.getX()));
        shapeRenderer.translate((float) ball.getX(),(float) ball.getY(),0);
        shapeRenderer.end();
        batch.end();
        ball.setX(ball.getX()+ball.getAngle()+ball.getSpeed());
        ball.setY(ball.getX()+ball.getSpeed()+ball.getAngle());
        dispose();
        stage.act();
        stage.draw();
    }

    public void update(float delta) {
        stage.act(delta);
    }

    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width, height, true);
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

        stage.dispose();
        shapeRenderer.dispose();

    }


    @Override
    public Ball createBall(int xPos,int yPos) {
        return new Ball(xPos, yPos, 60f, 1, 10);
    }
}
