package prototype.src.desktop;

import Controller.ISwitchController;
import Controller.PlayerController;
import Model.GameObjects.IModel;
import Model.GameObjects.IPlayer;
import Model.GameObjects.Player;
import View.IView;
import View.MenuView.MenuView;
import View.MenuView.OptionView;
import View.ObjectView.BoardView;
import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.OrthographicCamera;
import prototype.src.desktop.IScreen;

import java.util.ArrayList;


public class Roungout extends Game {

    public static final String TITLE = "Roungout";
    public static final float VERSION = 0.3f;
    public static int WIDTH =1980;
    public static int HEIGHT =1080;
    public static boolean RESIZE = true;

    private MenuView MenuView;
    public OrthographicCamera camera;

    private ArrayList<ISwitchController> controllers = new ArrayList<ISwitchController>();
    private ArrayList<IView> viewers = new ArrayList<IView>();

    //Should the controller use IPlayers instead??
    private IModel player1 = new Player(20,10,0,0,5);
    private IModel player2 = new Player(20,10,0,0,5);

    public void inintControllers(){

        PlayerController playerController = new PlayerController(viewers,controllers,player1,player2);
        controllers.add(0,playerController);
        playerController.updateControllerList(controllers);
    }


    @Override
    public void create() {
        camera = new OrthographicCamera();
        camera.setToOrtho(false, WIDTH, HEIGHT);
    }

    public void render() {
        super.render();
    }

    public void dispose() {

    }
}
