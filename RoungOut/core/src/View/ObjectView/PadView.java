package View.ObjectView;

import Model.GameObjects.IPad;
import Model.GameObjects.IPlayer;
import Model.GameObjects.Pad;
import View.IView;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.FitViewport;

/**
 * Created by DukeA on 2017-05-02.
 */
public class PadView implements Screen, IPad, IView {

    private Pad pad;
    private Pad pad2;
    private Stage stage;
    private SpriteBatch batch;
    private ShapeRenderer shapeRenderer;
    private int WIDTH;
    private int HEIGHT;


    public PadView(int width, int height) {
        this.WIDTH = width;
        this.HEIGHT = height;

        pad = createPad(WIDTH / 2 - 350, HEIGHT / 2);
        pad2 = createPad(WIDTH / 2 - 450, HEIGHT / 2);
        this.stage = new Stage(new FitViewport(WIDTH, HEIGHT));

    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(1f, 1f, 1f, 1f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);
        stage.act(Gdx.graphics.getDeltaTime());
        stage.draw();
        update(delta);
        batch = new SpriteBatch();
        batch.begin();
        shapeRenderer = new ShapeRenderer();
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(Color.BLUE);
        shapeRenderer.rect((float) pad.getPadXPos()
                , (float) pad.getPadYPos()
                , (float) pad.getWidth()
                , (float) pad.getLength());
        shapeRenderer.rect((float) pad2.getPadXPos()
                , (float) pad2.getPadYPos()
                , (float) pad2.getWidth()
                , (float) pad2.getLength());

        shapeRenderer.end();
        batch.end();

    }

    public void update(float delta) {
        stage.act(delta);

    }

    @Override
    public void resize(int width, int height) {

        stage.getViewport().update(width, height);
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

    }

    @Override
    public Pad createPad(int xPos, int yPos) {
        return new Pad(80f, 15f, xPos, yPos, 0);
    }

    @Override
    public void keyWasPressed(int i) {

    }
}
