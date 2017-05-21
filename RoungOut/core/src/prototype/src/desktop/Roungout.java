package prototype.src.desktop;

import Controller.ControllerHandler;
import Controller.IControllHandeling;
import Controller.GameController;

import Controller.IController;
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


    public IController gameController;


    private ArrayList<IViews> viewers = new ArrayList<IViews>();


    private BoardView view;
    private Board board;

    public Board getBoard(){
        return board;
    }
    public IController getGameController(){
        return gameController;
    }

    public void inintControllers() {
        List<IPlayer> players = new ArrayList<IPlayer>(board.getPlayers());

        ControllerHandler handler = new ControllerHandler();

        GameController gameController = new GameController(viewers,players.get(0),players.get(1),handler);
        ArrayList<IControllHandeling> controllers = new ArrayList<IControllHandeling>();

        controllers.add(gameController);
        //Init the handler with the controllers
        handler.setControllers(controllers);
    }


    @Override
    public void create() {

        //board = board.getBoard() // needed inorder to update the board
        splashScreen = new SplashView(WIDTH, HEIGHT, this); //set input
        camera = new OrthographicCamera();
        camera.setToOrtho(false, WIDTH, HEIGHT);
        board = new Board(WIDTH, HEIGHT);
        view = new BoardView(WIDTH, HEIGHT,this);
        viewers.add(view);


        //InitControllers
        List<IPlayer> players = new ArrayList<IPlayer>(board.getPlayers());
        ControllerHandler handler = new ControllerHandler();
        gameController = new GameController(viewers,players.get(0),players.get(1),handler);

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
