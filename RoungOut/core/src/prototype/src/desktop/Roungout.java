package prototype.src.desktop;

import Controller.ControllerHandler;
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

    public enum EnumIndexes {
        PLAYER_CONTROLLER(0),
        MENU_CONTROLLER(1),
        OPTIONS_CONTOLLERS(2);

        private int indexvalue;
        EnumIndexes(int i ) {
            this.indexvalue = i;
        }
        public int getIndexvalue(){
            return this.indexvalue;
        }
    }

    public void inintControllers(){
        boardView = new BoardView(WIDTH,HEIGHT);
        viewers.add(boardView);
        ControllerHandler handler = new ControllerHandler();


        PlayerController playerController = new PlayerController(viewers,EnumIndexes.PLAYER_CONTROLLER.getIndexvalue(),player1,player2);

        ControllerHandler handler = new ControllerHandler();
        handler.addController(EnumIndexes.PLAYER_CONTROLLER.getIndexvalue(),playerController);
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
