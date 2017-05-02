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
public class BallView implements Screen {

    private IBall ball;
    private Stage stage;
    private SpriteBatch batch;
    private ShapeRenderer shapeRenderer;
    private OrthographicCamera camera;

    public BallView(int WIDTH, int HEIGHT) {
        this.camera = camera;
        ball = new Ball(WIDTH/2,HEIGHT/2,100f,0,0);
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
                (float) ( ball.getball().getX() - 200+ball.getball().getSpeed())
                , (float) (ball.getball().getY() - 450 +ball.getball().getSpeed())
                , (float)((ball.getball().getRadius()*2)*ball.getball().getY()/ball.getball().getX())
                , (float) (ball.getball().getRadius() * ball.getball().getY() / ball.getball().getX()));
        shapeRenderer.end();
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(1f, 1f, 1f, 1f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);
        update(delta);
        batch = new SpriteBatch();

        batch.setProjectionMatrix(camera.combined);
        batch.begin();
        show();
        batch.end();
        stage.act();
        stage.draw();
    }

    public void update(float delta) {
        stage.act(delta);
        ball.getball().setPosition(ball.getball().getX() + ball.getball().getSpeed()
                ,ball.getball().getY()+ball.getball().getSpeed());
        camera.update();
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

}
