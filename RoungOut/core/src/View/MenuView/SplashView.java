package View.MenuView;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * Created by DukeA on 2017-05-09.
 */
public class SplashView implements Screen {

    private int WIDTH;
    private int HEIGHT;
    private Texture splashtexture;
    private Sprite sprite;
    private SpriteBatch batch;

    public SplashView(int WIDTH, int HEIGHT) {
        this.WIDTH = WIDTH;
        this.HEIGHT = HEIGHT;
    }


    @Override
    public void show() {
        splashtexture = new Texture(Gdx.files.internal(""));
        sprite = new Sprite(splashtexture);
        sprite.setSize(WIDTH,HEIGHT);
        batch = new SpriteBatch();
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0,0,0,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.begin();
        Splas
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
