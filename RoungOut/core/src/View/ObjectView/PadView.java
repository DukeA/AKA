package View.ObjectView;

import Model.GameObjects.IPlayer;
import Model.GameObjects.Pad;
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
public class PadView implements Screen ,IPlayer{

    private Pad player;
    private Stage stage;
    private SpriteBatch batch;
    private ShapeRenderer shapeRenderer;
    private int WIDTH;
    private int HEIGHT;


    public PadView(int width,int height) {
        this.WIDTH = width;
        this.HEIGHT = height;

        player =getPad();
        this.stage = new Stage(new FitViewport(WIDTH,HEIGHT));

    }

    @Override
    public void show() {
        shapeRenderer = new ShapeRenderer();
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(Color.BLUE);
        shapeRenderer.rect((float) player.getPadXPos()
                            ,(float)player.getPadYPos()
                            ,(float)player.getWidth()
                            , (float)player.getLength());
        shapeRenderer.end();
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(1f,1f,1f,1f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);
        update(delta);
        batch = new SpriteBatch();
        batch.begin();
        show();
        batch.end();
        stage.act();
        stage.draw();
    }
    public void update(float delta)  {
        stage.act(delta);
        player.setPadXPos(player.getPadXPos()+player.getPadSpeed());
        player.setPadYPos(player.getPadXPos()+player.getPadSpeed());

    }

    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width,height);
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
    public int getPoints() {
        return 0;
    }

    @Override
    public Pad getPad() {
        return new Pad(30f,60f ,WIDTH/2,HEIGHT/2,0);
    }
}
