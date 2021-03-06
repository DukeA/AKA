package View.MenuView;

import AbstractGameComponents.AGame;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.TimeUtils;

/**
 * @author Adam
 * Created on 2017-05-09.
 */
public class SplashView implements Screen {

    private int WIDTH;
    private int HEIGHT;
    private long startTime;
    private AGame game;
    private Stage stage;
    private MenuView view;
    private Texture splashtexture;
    private Sprite sprite;
    private SpriteBatch batch;


    /**
     * The Splash screen is the Constructor for the SplashView
     * which takes in as in parameter the value of the WIDTH and HEIGHT
     * of the screen ,It also takes the referance of the game.
     * @param WIDTH
     * @param HEIGHT
     * @param game
     */

    public SplashView(int WIDTH, int HEIGHT, AGame game) {
        this.WIDTH = WIDTH;
        this.HEIGHT = HEIGHT;
        this.game = game;
        view = new MenuView(WIDTH,HEIGHT,game);
        splashtexture = new Texture(Gdx.files.internal(".\\core\\src\\Assets\\splash.png"));
        sprite = new Sprite(splashtexture);
        sprite.setSize(WIDTH,HEIGHT);
        stage = new Stage();
        startTime = TimeUtils.millis();

    }


    @Override
    public void show() {

    }

    /**
     * The clss render the screen render the screen in delta time until 10's
     * When it sudenly is going to change the view
     * ,when the time for the Screen times out
     * @param delta
     */

    @Override
    public void render(float delta) {

        Gdx.gl.glClearColor(0.14f,0.23f,0.27f,0);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

            batch = new SpriteBatch();
            batch.begin();
            sprite.draw(batch);
            batch.end();


            if (TimeUtils.timeSinceMillis(startTime) > 1000){
              game.setScreen(view);
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
