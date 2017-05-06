package prototype.src.desktop;

import View.MenuView.MenuView;
import View.MenuView.OptionView;
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

    private MenuView MenuView;
    private BoardView iScreen;
    private OptionView OptionView;
    public OrthographicCamera camera;

    @Override
    public BoardView getBorderView() {
       return iScreen = new BoardView(WIDTH,HEIGHT);
    }

    @Override
    public MenuView getMenuView() {
        return MenuView = new MenuView(WIDTH,HEIGHT);

    }

    @Override
    public OptionView getOptionView() {
        return OptionView = new OptionView(WIDTH,HEIGHT);
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
