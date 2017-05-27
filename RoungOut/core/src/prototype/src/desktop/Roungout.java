package prototype.src.desktop;

/**
 * @author Adam
 * @author Ken
 * @author Alex
 * Created at the start of the project, consistently modified during the duration of the project
 */

import AbstractGameComponents.AGameController;
import AbstractGameComponents.AMenuController;
import AbstractGameComponents.AOptionsController;
import Controller.*;

import AbstractGameComponents.AGame;
import Model.GameObjects.*;
import View.MenuView.SplashView;
import View.ObjectView.BoardView;
import IViews.IViews;
import Model.GameObjects.IPlayer;
import com.badlogic.gdx.graphics.OrthographicCamera;

import java.util.ArrayList;

import java.util.List;


public class Roungout extends AGame {
    /**
     * The initializer of the whole game, it is here everything in the game start
     */


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

    private BoardView BoardView;
    private Board board;

    private GameController gameController;
    private MenuController menuController;
    private OptionsController optionsController;

    /**
     * Getter, gets the board
     * @return Board
     */
    public Board getBoard(){
        return board;
    }

    /**
     * Getter, gets the gameController
     * @return The gameController
     */
    @Override
    public AGameController getGameController(){
        return gameController;
    }

    /**
     * Getter, gets the menuController
     * @return The menuController
     */
    @Override
    public AMenuController getMenuController() {return menuController;}

    /**
     * Getter, gets the menuController
     * @return The menuController
     */
    @Override
    public AOptionsController getOptionsController() {return optionsController;}

    /**
     * Part of the framework, this happens the when the Class is created (Comes from AGame, AGame extends this from Game)
     */
    @Override
    public void create() {

        //board = board.getBoard() // needed inorder to update the board
        camera = new OrthographicCamera();
        camera.setToOrtho(false, WIDTH, HEIGHT);
        board = new Board(WIDTH, HEIGHT);

        //board.createSampleBoard(WIDTH, HEIGHT);         // Sample board creation here. Otherwise BoardTest is screwed up.
        BoardView = new BoardView(WIDTH, HEIGHT,this);
        gameControllerViewers.add(BoardView);



        //InitControllers
        //Init handler
        ControllerHandler handler = new ControllerHandler();

        List<IPlayer> players = new ArrayList<IPlayer>(board.getPlayers());
        gameController = new GameController(gameControllerViewers,players.get(0),players.get(1),handler);
        menuController = new MenuController(menuControllerViewers,handler);
        optionsController = new OptionsController(optionsControllerViewers,handler);



        //put all controllers in a list to the handler
        ArrayList<IControllHandeling> controllers = new ArrayList<IControllHandeling>();
        controllers.add(gameController);
        controllers.add(menuController);
        controllers.add(optionsController);
        //Init the handler with the controllers
        handler.setControllers(controllers);


        splashScreen = new SplashView(WIDTH, HEIGHT, this); //set input
        this.setScreen(splashScreen);
    }

    /**
     * Part of the framework, inherited from Game
     */
    public void render() {
        super.render();
    }

    /**
     * Part of the framework, inherited from Game
     */
    public void dispose() {
        super.dispose();
    }


}
