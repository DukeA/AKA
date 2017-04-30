package prototype.src.desktop;

import View.ObjectView.BoardView;
import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;


public class Roungout extends Game {

    public static final String TITLE = "Roungout";
    public static final float VERSION =0.1f;
    public static int WIDTH =1980;
    public static int HEIGHT =1080;
    public static boolean RESIZE = false;

    public OrthographicCamera camera;
    public SpriteBatch batch;

    public enum Screen {
        MainMenu,
        InGame,

    }
    private Screen screen =Screen.InGame;

    @Override
    public void create() {
        camera = new OrthographicCamera();
        camera.setToOrtho(false,WIDTH,HEIGHT);

        this.setScreen(new BoardView(this));
    }

    public void render() {
        super.render();
    }

    public void dispose() {

    }
}
