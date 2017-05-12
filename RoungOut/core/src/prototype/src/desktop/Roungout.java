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


public class Roungout extends Game {

    public static final String TITLE = "Roungout";
    public static final float VERSION = 0.3f;
    public static int WIDTH =1980;
    public static int HEIGHT =1080;
    public static boolean RESIZE = true;
    public static boolean FULLSCREEN = true;

    private MenuView MenuView;
    public OrthographicCamera camera;
    public IViews boardView;

    private ArrayList<ISwitchController> controllers = new ArrayList<ISwitchController>();
    private ArrayList<IViews> viewers = new ArrayList<IViews>();

    private BoardView view;

    private Board board;




    public void inintControllers(){
        boardView = new BoardView(WIDTH,HEIGHT);
        viewers.add(boardView);

        PlayerController playerController = new PlayerController(viewers,controllers,player1,player2);
        controllers.add(0,playerController);
        playerController.updateControllerList(controllers);

    }


    @Override
    public void create() {
        GameObjects();





        //TODO MAKE GAME LOOP
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

    public void GameObjects() {
        board = new Board(WIDTH,HEIGHT);
        board.addPlayer(new Player(80f, 30f, WIDTH / 2 - 350, HEIGHT / 2, 0));
        board.addPlayer(new Player(80f, 30f, WIDTH / 2 - 350, HEIGHT / 2, 0));
        board.addBrick(new Brick(WIDTH/2-40,HEIGHT/2,30,30));
        board.addBrick(new Brick(WIDTH/2,HEIGHT/2,30,30));
        board.addBrick(new Brick(WIDTH/2+40,HEIGHT/2,30,30));
        board.addBrick(new Brick(WIDTH/2-40,HEIGHT/2-40,30,30));
        board.addBrick(new Brick(WIDTH/2,HEIGHT/2-40,30,30));
        board.addBrick(new Brick(WIDTH/2+40,HEIGHT/2-40,30,30));
        board.addBrick(new Brick(WIDTH/2-40,HEIGHT/2+40,30,30));
        board.addBrick(new Brick(WIDTH/2,HEIGHT/2+40,30,30));
        board.addBrick(new Brick(WIDTH/2+40,HEIGHT/2+40,30,30));
        board.addBall(new Ball(WIDTH / 2 - 250, HEIGHT / 2 + 20, 30f, 1, 100));
    }
}
