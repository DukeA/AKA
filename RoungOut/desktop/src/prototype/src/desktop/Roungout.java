package prototype.src.desktop;

import View.ObjectView.GameView;
import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.OrthographicCamera;


public class Roungout extends Game implements IScreen {

    public static final String TITLE = "Roungout";
    public static final float VERSION =0.1f;
    public static int WIDTH =1980;
    public static int HEIGHT =1080;
    public static boolean RESIZE = true;

    private GameView iScreen;
    public OrthographicCamera camera;

    @Override
    public GameView getBorderView() {
        iScreen = new GameView(WIDTH,HEIGHT);
        return iScreen;
    }



    @Override
    public void create() {
        camera = new OrthographicCamera();
        camera.setToOrtho(false,WIDTH,HEIGHT);
        this.setScreen(getBorderView());
    }

    public void render() {
        super.render();
    }

    public void dispose() {

    }
}
