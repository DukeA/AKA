package prototype.src.desktop;

/*<<<<<<< HEAD
import Controller.*;

import AbstractGame.AGame;
=======*/
import AbstractGameComponents.AGameController;
import AbstractGameComponents.AMenuController;
import AbstractGameComponents.AOptionsController;
import Controller.*;

import AbstractGameComponents.AGame;
//>>>>>>> Change_Controller
import Model.GameObjects.*;
import View.MenuView.SplashView;
import View.ObjectView.BoardView;
import IViews.IViews;
import Model.GameObjects.IPlayer;
import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.OrthographicCamera;

import java.awt.*;
import java.util.ArrayList;

import java.util.List;


public class Roungout extends AGame {

    public static final String TITLE = "Roungout";
    public static final float VERSION = 0.3f;
    public static int WIDTH = 1680;
    public static int HEIGHT = 1050;
    public static boolean RESIZE = true;
    public static boolean FULLSCREEN = false;

    private SplashView splashScreen;
    public OrthographicCamera camera;
    private ArrayList<IViews> gameControllerViewers = new ArrayList<IViews>();
    private ArrayList<IViews> menuControllerViewers = new ArrayList<IViews>();
    private ArrayList<IViews> optionsControllerViewers = new ArrayList<IViews>();

/*<<<<<<< HEAD
    //create controllers
    public GameController gameController;
    public OptionsController optionsController;
    public MenuController menuController;


    private ArrayList<IViews> viewers = new ArrayList<IViews>();


    private BoardView view;
=======*/
    private BoardView BoardView;
//>>>>>>> Change_Controller
    private Board board;

    private GameController gameController;
    private MenuController menuController;
    private OptionsController optionsController;

    public Board getBoard(){
        return board;
    }
/*<<<<<<< HEAD
    public GameController getGameController(){
        return gameController;
    }
    public OptionsController getOptionsController() {return optionsController;}
    public MenuController getMenuController () {return menuController;}

   /* public void initControllers() {
        List<IPlayer> players = new ArrayList<IPlayer>(board.getPlayers());

        ControllerHandler handler = new ControllerHandler();

        GameController gameController = new GameController(viewers,players.get(0),players.get(1),handler);
        ArrayList<IControllHandeling> controllers = new ArrayList<IControllHandeling>();

        controllers.add(gameController);
        //Init the handler with the controllers
        handler.setControllers(controllers);
    }*/

//=======
    @Override
    public AGameController getGameController(){
        return gameController;
    }
    @Override
    public AMenuController getMenuController() {return menuController;}
    @Override
    public AOptionsController getOptionsController() {return optionsController;}
//>>>>>>> Change_Controller

    @Override
    public void create() {

        //board = board.getBoard() // needed inorder to update the board
        camera = new OrthographicCamera();
        camera.setToOrtho(false, WIDTH, HEIGHT);
        board = new Board(WIDTH, HEIGHT);
/*<<<<<<< HEAD
        view = new BoardView(WIDTH, HEIGHT,this);
        viewers.add(view);
=======*/
        //board.createSampleBoard(WIDTH, HEIGHT);         // Sample board creation here. Otherwise BoardTest is screwed up.
        BoardView = new BoardView(WIDTH, HEIGHT,this);
        gameControllerViewers.add(BoardView);

//>>>>>>> Change_Controller


        //InitControllers
        //Init handler
        ControllerHandler handler = new ControllerHandler();
/*<<<<<<< HEAD

        //init gameController
        List<IPlayer> players = new ArrayList<IPlayer>(board.getPlayers());
        gameController = new GameController(viewers,players.get(0),players.get(1),handler);
=======*/
        List<IPlayer> players = new ArrayList<IPlayer>(board.getPlayers());
        gameController = new GameController(gameControllerViewers,players.get(0),players.get(1),handler);
        menuController = new MenuController(menuControllerViewers,handler);
        optionsController = new OptionsController(optionsControllerViewers,handler);

//>>>>>>> Change_Controller


        //put all controllers in a list to the handler
        ArrayList<IControllHandeling> controllers = new ArrayList<IControllHandeling>();
        controllers.add(gameController);
        controllers.add(menuController);
        controllers.add(optionsController);
        //Init the handler with the controllers
        handler.setControllers(controllers);

/*<<<<<<< HEAD
        //Sets the input processor to this index (GAME CONTROLLER)
        handler.getcontrollers().get(0).changeInputProcessor();


=======*/
        splashScreen = new SplashView(WIDTH, HEIGHT, this); //set input
//>>>>>>> Change_Controller
        this.setScreen(splashScreen);
    }


    public void render() {
        super.render();
    }

    public void dispose() {
        super.dispose();
    }


}
