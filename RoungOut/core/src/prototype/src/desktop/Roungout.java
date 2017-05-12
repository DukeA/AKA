package prototype.src.desktop;

import Controller.ControllerHandler;
import Controller.IController;
import Controller.PlayerController;
import Model.GameObjects.IModel;
import Model.GameObjects.Player;
import View.IView;
import View.MenuView.MenuView;
import View.ObjectView.BoardView;
import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.OrthographicCamera;

import java.util.ArrayList;


public class Roungout extends Game {

    public static final String TITLE = "Roungout";
    public static final float VERSION = 0.3f;
    public static int WIDTH =1980;
    public static int HEIGHT =1080;
    public static boolean RESIZE = true;

    private MenuView MenuView;
    public OrthographicCamera camera;
    public IView boardView;


    private ArrayList<IView> viewers = new ArrayList<IView>();

    private BoardView view;

    //Should the controller use IPlayers instead??
    private IModel player1 = new Player(20,10,0,0,5);
    private IModel player2 = new Player(20,10,0,0,5);


    public void inintControllers(){
        boardView = new BoardView(WIDTH,HEIGHT);
        viewers.add(boardView);
        //ControllerHandler handler = new ControllerHandler();
        /*ArrayList<EnumIndexes> indexes = new ArrayList<EnumIndexes>();
        indexes.add(0,EnumIndexes.PLAYER_CONTROLLER);
        indexes.add(1,EnumIndexes.MENU_CONTROLLER);
        indexes.add(2,EnumIndexes.OPTIONS_CONTOLLERS);
        */
        ControllerHandler handler = new ControllerHandler();

        ArrayList<IController> controllers = new ArrayList<IController>();
        PlayerController playerController = new PlayerController(viewers,player1,player2, handler);
        controllers.add(playerController);


    }


    @Override
    public void create() {
        camera = new OrthographicCamera();
        view = new BoardView(WIDTH,HEIGHT);
        camera.setToOrtho(false, WIDTH, HEIGHT);
        this.setScreen(view);
        inintControllers();
    }

    public void render() {
        super.render();
    }

    public void dispose() {

    }
}
