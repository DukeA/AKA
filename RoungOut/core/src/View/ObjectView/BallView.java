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

    public BallView(int WIDTH, int HEIGHT) {
        this.WIDTH = WIDTH;
        this.HEIGHT =HEIGHT;
        ball = getball();
        this.stage = new Stage(
                new FitViewport(WIDTH / 2 + 10
                        , HEIGHT / 2 + 10));
    }

    @Override
    public void show() {
        shapeRenderer = new ShapeRenderer();
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(Color.RED);
        shapeRenderer.ellipse(
                (float) ( ball.getX())
                , (float) (ball.getY())
                , (float)((ball.getRadius()*2)*ball.getY()/ball.getX())
                , (float) (ball.getRadius() * ball.getY() / ball.getX()));
        shapeRenderer.end();
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(1f, 1f, 1f, 1f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);
        update(delta);
        batch = new SpriteBatch();

        batch.begin();
        show();
        batch.end();
        stage.act();
        stage.draw();
    }

    public void update(float delta) {
        stage.act(delta);
        ball.setPosition(ball.getX() + ball.getSpeed()
                ,ball.getY()+ball.getSpeed());
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
    }


    @Override
    public Ball getball() {
        return new Ball(WIDTH,HEIGHT,10f,0,0);
    }
}
