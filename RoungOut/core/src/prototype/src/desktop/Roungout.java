package prototype.src.desktop;

import AbstractController.AGameController;
import AbstractController.AMenuController;
import AbstractController.AOptionsController;
import Controller.*;

import AbstractGame.AGame;
import Model.GameObjects.*;
import View.MenuView.SplashView;
import View.ObjectView.BoardView;
import IViews.IViews;
import Model.GameObjects.IPlayer;
import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.OrthographicCamera;
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

    private BoardView BoardView;
    private Board board;

    private GameController gameController;
    private MenuController menuController;
    private OptionsController optionsController;

    public Board getBoard(){
        return board;
    }
    @Override
    public AGameController getGameController(){
        return gameController;
    }
    @Override
    public AMenuController getMenuController() {return menuController;}
    @Override
    public AOptionsController getOptionsController() {return optionsController;}

    @Override
    public void create() {

        //board = board.getBoard() // needed inorder to update the board
        splashScreen = new SplashView(WIDTH, HEIGHT, this); //set input
        camera = new OrthographicCamera();
        camera.setToOrtho(false, WIDTH, HEIGHT);
        board = new Board(WIDTH, HEIGHT);
        board.createSampleBoard(WIDTH, HEIGHT);         // Sample board creation here. Otherwise BoardTest is screwed up.
        BoardView = new BoardView(WIDTH, HEIGHT,this);
        gameControllerViewers.add(BoardView);


        //InitControllers
        List<IPlayer> players = new ArrayList<IPlayer>(board.getPlayers());
        ControllerHandler handler = new ControllerHandler();
        gameController = new GameController(gameControllerViewers,players.get(0),players.get(1),handler);
        menuController = new MenuController(menuControllerViewers,handler);//here
        optionsController = new OptionsController(optionsControllerViewers,handler);//here


        Gdx.input.setInputProcessor(gameController);
        ArrayList<IControllHandeling> controllers = new ArrayList<IControllHandeling>();
        controllers.add(gameController);
        //Init the handler with the controllers
        handler.setControllers(controllers);

        //Sets the input processor to this index (GAME CONTROLLER)
        handler.getcontrollers().get(0).changeInputProcessor();

        this.setScreen(splashScreen);
    }


    public void render() {
        super.render();

    }

    public void dispose() {
        super.dispose();
    }


}
