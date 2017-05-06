package View.MenuView;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Table;

/**
 * Created by DukeA on 2017-05-05.
 */
public class OptionView implements Screen {

    private int WIDTH;
    private int HEIGHT;
    private SpriteBatch batch;
    private Table table;
    private Stage stage;
    private BitmapFont font;

    public OptionView(int WIDTH, int HEIGHT) {
        this.WIDTH = WIDTH;
        this.HEIGHT = HEIGHT;
        font = new BitmapFont();
        stage = new Stage();
        batch = new SpriteBatch();
    }

    @Override
    public void show() {
        table = new Table();
        table.setFillParent(true);
        table.top();
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0,0,1f,1f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch = new SpriteBatch();

        batch.begin();
        font.draw(batch,"Options", WIDTH/2, HEIGHT/2);
        stage.act();
        stage.draw();
        batch.end();
    }

    @Override
    public void resize(int width, int height) {

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
}
