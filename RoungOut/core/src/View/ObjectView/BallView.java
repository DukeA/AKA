package View.ObjectView;

import Model.GameObjects.Ball;
import Model.GameObjects.IModel;
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

    private IModel model;
    private Stage stage;
    private SpriteBatch batch;
    private ShapeRenderer shapeRenderer;
    private OrthographicCamera camera;

    public BallView(int WIDTH, int HEIGHT, OrthographicCamera camera) {
        this.camera = camera;
        model = new Ball(WIDTH /    2, HEIGHT / 2, 10, 10, 10);
        this.stage = new Stage(
                new FitViewport(WIDTH / 2 + 10
                        , HEIGHT / 2 + 10, camera));
    }

    @Override
    public void show() {
        shapeRenderer = new ShapeRenderer();
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(Color.RED);
        shapeRenderer.ellipse((float) ( model.getX() - 200+model.getSpeed()), (float) (model.getY() - 450 +model.getSpeed())
                , 100f, (float) (50f * model.getY() / model.getX()));
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
        model.setPosition(model.getX() + model.getSpeed()
                ,model.getY()+model.getSpeed());
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
