package prototype.src.desktop;

import View.ObjectView.BoardView;
import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.OrthographicCamera;
import prototype.src.desktop.IScreen;


public class Roungout extends Game implements IScreen {

    public static final String TITLE = "Roungout";
    public static final float VERSION =0.2f;
    public static int WIDTH =1980;
    public static int HEIGHT =1080;
    public static boolean RESIZE = true;

    private BoardView iScreen;
    public OrthographicCamera camera;

    @Override
    public BoardView getBorderView() {
        iScreen = new BoardView(WIDTH,HEIGHT);
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
