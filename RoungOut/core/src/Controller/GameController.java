package Controller;

import View.ObjectView.IViews;
import Model.GameObjects.IPlayer;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;

import java.util.ArrayList;


/**
 * Created by Alex on 2017-04-28.
 */
public class GameController implements IController{
    //The list will only contain unique subscribers and this is guaranteed by the
    //addListener and removeListener methods

    private ArrayList<IViews> viewSubscribers = new ArrayList<IViews>();

    //    private ArrayList<IModel> modelSubscribers = new ArrayList<IModel>();
   // private ArrayList<ISwitchController> controllers = new ArrayList<ISwitchController>();

    private final EnumIndexes typeOfMenu = EnumIndexes.GAME_CONTROLLER; //Static Enum
    private IHandler handler;

    private IPlayer Player1;
    private IPlayer Player2;

    //Keys, made this way inorder to rebind them in the future
    private int P1Right;
    private int P1Left;
    private int P2Right;
    private int P2Left;

    //KeyState, used to track if a key is being held down or not since InputProcessor does'nt do that
    private boolean isP1RightDown;
    private boolean isP1LeftDown;
    private boolean isP2RightDown;
    private boolean isP2LeftDown;

    private String latestKey = " ";//init with blank
    public String latestKeyPressed()
    {
        //Used for debug
        return latestKey;
    }


    @Override
    public boolean keyDown(int keycode) {
        /**
         *  This will be called when a key is pressed down (keyboard) by libgdx's InputProcessor
         */
        latestKey = Input.Keys.toString(keycode);

        if (keycode== P1Left){
            isP1LeftDown=true;

            Player1.moveLeft();
            updateAllViews(Gdx.graphics.getDeltaTime());
        }

        if (keycode== P1Right){
            isP1RightDown = true;

            Player1.moveRight();
            updateAllViews(Gdx.graphics.getDeltaTime());
        }

        if (keycode== P2Left){
            isP2LeftDown = true;

            Player2.moveLeft();
            updateAllViews(Gdx.graphics.getDeltaTime());
        }

        if (keycode== P2Right){
            isP2RightDown = true;

            Player2.moveRight();
            updateAllViews(Gdx.graphics.getDeltaTime());
        }

        if (keycode== Input.Keys.ESCAPE){
            handler.callSetNewInput(EnumIndexes.MENU_CONTROLLER);
            isGameRunning =false;
            //if ESC -> Set the menu Controller to active input via handler
        }

        System.out.println(latestKey);
        /**
         *
         *  It would be much simpler if the return value of the interfaced could be an int and
         *  simply import the "com.badlogic.gdx.Input" library to reach the Inputs.Keys field
         *  UPDATE: Decided to send ints instead of chars since that was simpler, char still stored for debug
         *  UPDATE2: Decided that the controller should send explicit calls to the objects listening to the controller
         *
         */
        return true;
    }

    //Helper method, code reuse
    private void updateAllViews(float deltaTime) {
        //Call update to all views
        for (IViews view : viewSubscribers) {
            view.update(deltaTime);
        }
    }

    @Override
    public void changeInputProcessor() {
        Gdx.input.setInputProcessor(this);
        isGameRunning = true;
        gameLoop();
    }

    @Override
    public EnumIndexes getTypeOfMenu(){ return typeOfMenu;}

    //We don't need these inputs
    @Override
    public boolean keyUp(int keycode) {
        if (keycode== P1Left){
            isP1LeftDown=false;
        }

        if (keycode== P1Right){
            isP1RightDown = false;
        }

        if (keycode== P2Left){
            isP2LeftDown = false;
        }

        if (keycode== P2Right) {
            isP2RightDown = false;
        }

        return true;
    }

    @Override
    public boolean keyTyped(char character) { return false; }
    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) { return false; }
    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }
    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }
    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }
    @Override
    public boolean scrolled(int amount) {
        return false;
    }
    //End of these inputs

    void movePlayers(){
    //Help function, checks if the key is being held down and if so, move accordingly
        if (isP1LeftDown){
            Player1.moveLeft();
        }

        if (isP1RightDown){
            Player1.moveRight();
        }

        if (isP2LeftDown){
            Player2.moveLeft();
        }

        if (isP2RightDown){
            Player2.moveRight();
        }

    }



    //GameLoopThe thing that makes the game run
    private volatile boolean isGameRunning;
    private void gameLoop(){
        float deltaTime;
        while (isGameRunning){
            deltaTime = Gdx.graphics.getDeltaTime();
            movePlayers(); //moves the players
            //board.update(deltaTime); //not receiving a board as of now
            updateAllViews(deltaTime);
        }
    }


    public GameController(ArrayList<IViews> views, IPlayer P1, IPlayer P2, IHandler handler) {

        Gdx.input.setInputProcessor(this);
        //This is the only Controller that claims the inputProcessor

        this.handler = handler;

        this.Player1=P1;
        this.Player2=P2;

        this.P1Left = Input.Keys.A;
        this.P1Right = Input.Keys.D;
        this.P2Left = Input.Keys.J;
        this.P2Right = Input.Keys.L;

        this.isP1RightDown = false;
        this.isP1LeftDown = false;
        this.isP2RightDown = false;
        this.isP2LeftDown = false;

        //this.controllers=controllerList;
        //this.modelSubscribers=models;
        this.viewSubscribers=views;
        //Makes it so LibGdx sends calls to this controller
        //(Adds the created PlayerController as a listener for LibGdx)


        this.isGameRunning =false;
    }
}
