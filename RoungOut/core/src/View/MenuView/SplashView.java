package View.MenuView;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.TimeUtils;
import prototype.src.desktop.Roungout;

/**
 * Created by DukeA on 2017-05-09.
 */
public class SplashView implements Screen {

    private int WIDTH;
    private int HEIGHT;
    private Game game;
    private Stage stage;
    private MenuView view;
    private Texture splashtexture;
    private Sprite sprite;
    private SpriteBatch batch;

    public SplashView(int WIDTH, int HEIGHT, Game game) {
        this.WIDTH = WIDTH;
        this.HEIGHT = HEIGHT;
        this.game = game;
        view = new MenuView(WIDTH,HEIGHT,game);
        splashtexture = new Texture(Gdx.files.internal(".\\core\\src\\Assets\\splash.png"));
        sprite = new Sprite(splashtexture);
        sprite.setSize(WIDTH,HEIGHT);
        stage = new Stage();

    }


    @Override
    public void show() {




    }

    @Override
    public void render(float delta) {

        Gdx.gl.glClearColor(0.14f,0.23f,0.27f,0);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
            batch = new SpriteBatch();
            batch.begin();
            sprite.draw(batch);
            batch.end();
            if (Gdx.input.isTouched()) {
              game.setScreen(view);
              this.hide();
            }


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
        batch.dispose();
    }

}
