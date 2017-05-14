package prototype.src.desktop;

import Controller.ControllerHandler;
import Controller.IController;
import Controller.PlayerController;

import Model.GameObjects.*;
import View.MenuView.MenuView;
import View.ObjectView.BoardView;
import View.ObjectView.IViews;


import Model.GameObjects.IPlayer;
import Model.GameObjects.Player;

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


   private ArrayList<IViews> viewers = new ArrayList<IViews>();

   private BoardView view;
   private Board board;



    public void inintControllers(){
        List<IPlayer> players = new ArrayList<IPlayer>(board.getPlayers());
        boardView = new BoardView(WIDTH,HEIGHT,board);
        viewers.add(boardView);

        ControllerHandler handler = new ControllerHandler();

        PlayerController playerController = new PlayerController(viewers,players.get(0),players.get(1),handler);
        ArrayList<IController> controllers = new ArrayList<IController>();

        controllers.add(playerController);

        //Init the handler with the controllers
        handler.setControllers(controllers);
    }


    @Override
    public void create() {

        //board = board.getBoard() // needed inorder to update the board



        //TODO MAKE GAME LOOP
        board = new Board(WIDTH,HEIGHT);

        createSampleBoard(board);                   // Moved sample creation from Board class

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

    // Game sample data
    public void createSampleBoard(Board b) {
        b.addPlayer(new Player(80f, 30f, WIDTH / 2 - 350, HEIGHT / 2, 0));
        b.addPlayer(new Player(80f, 30f, WIDTH / 2 - 450, HEIGHT / 2, 0));
        b.addBrick(new Brick(WIDTH/2-40,HEIGHT/2,30,30));
        b.addBrick(new Brick(WIDTH/2,HEIGHT/2,30,30));
        b.addBrick(new Brick(WIDTH/2+40,HEIGHT/2,30,30));
        b.addBrick(new Brick(WIDTH/2-40,HEIGHT/2-40,30,30));
        b.addBrick(new Brick(WIDTH/2,HEIGHT/2-40,30,30));
        b.addBrick(new Brick(WIDTH/2+40,HEIGHT/2-40,30,30));
        b.addBrick(new Brick(WIDTH/2-40,HEIGHT/2+40,30,30));
        b.addBrick(new Brick(WIDTH/2,HEIGHT/2+40,30,30));
        b.addBrick(new Brick(WIDTH/2+40,HEIGHT/2+40,30,30));
        b.addBall(new Ball(WIDTH / 2 - 250, HEIGHT / 2 + 20, 30f, 1, 100));
    }

}
