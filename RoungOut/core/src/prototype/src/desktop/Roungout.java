package prototype.src.desktop;


import AbstractGameComponents.*;
import Controller.*;

//>>>>>>> Change_Controller
import Model.GameObjects.*;
import View.MenuView.SplashView;
import View.ObjectView.BoardView;
import IViews.IViews;
import Model.GameObjects.IPlayer;
import com.badlogic.gdx.*;
import com.badlogic.gdx.Graphics;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

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
    private Viewport viewport;

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
    public Roungout getRoungout(int Width, int Height,AGame game) {
        return new Roungout(Width,Height, game);
    }

    public Roungout(int WIDTH,int HEIGHT, AGame game) {
        this.WIDTH = WIDTH;
        this.HEIGHT = HEIGHT;
        Gdx.graphics.setWindowedMode(WIDTH,HEIGHT);
        create();
    }
    public Roungout() {

    }


    @Override
    public void create() {

        //board = board.getBoard() // needed inorder to update the board
        camera = new OrthographicCamera();
        camera.setToOrtho(false, WIDTH, HEIGHT);
        viewport = new FitViewport(WIDTH,HEIGHT,camera);
        board = new Board(WIDTH, HEIGHT);

        //board.createSampleBoard(WIDTH, HEIGHT);         // Sample board creation here. Otherwise BoardTest is screwed up.
        BoardView = new BoardView(WIDTH, HEIGHT,this);
        gameControllerViewers.add(BoardView);


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
    public void resizes(int width, int height) {
        viewport.update(width,height);
        camera.update();
    }


    public void render() {
        super.render();
    }

    public void dispose() {
        super.dispose();
    }


}
