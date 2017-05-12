package prototype.src.desktop;

import Controller.ISwitchController;
import Controller.PlayerController;
import Model.GameObjects.*;
import View.MenuView.MenuView;
import View.ObjectView.BoardView;
import View.ObjectView.IViews;
import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.OrthographicCamera;

import java.util.ArrayList;
import java.util.List;


public class Roungout extends Game {

    public static final String TITLE = "Roungout";
    public static final float VERSION = 0.3f;
    public static int WIDTH =1980;
    public static int HEIGHT =1080;
    public static boolean RESIZE = true;
    public static boolean FULLSCREEN = false;

    private MenuView MenuView;
    public OrthographicCamera camera;
    public IViews boardView;

    private ArrayList<ISwitchController> controllers = new ArrayList<ISwitchController>();
    private ArrayList<IViews> viewers = new ArrayList<IViews>();

    private BoardView view;

    private Board board;




    public void inintControllers(){
        List<Player> players = new ArrayList<Player>(board.getPlayers());
        boardView = new BoardView(WIDTH,HEIGHT,board);
        viewers.add(boardView);
        PlayerController playerController = new PlayerController(viewers,controllers
                ,players.get(0),players.get(1));
        controllers.add(0,playerController);
        playerController.updateControllerList(controllers);

    }


    @Override
    public void create() {





        //TODO MAKE GAME LOOP
        board = new Board(WIDTH,HEIGHT);
        camera = new OrthographicCamera();
        view = new BoardView(WIDTH,HEIGHT,board);
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
