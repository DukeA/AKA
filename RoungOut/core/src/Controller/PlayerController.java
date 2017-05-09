package Controller;

import Model.GameObjects.IModel;
import View.IView;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import org.omg.PortableInterceptor.INACTIVE;

import java.util.ArrayList;


/**
 * Created by Alex on 2017-04-28.
 */
public class PlayerController implements IPlayerController, ISwitchController{
    //The list will only contain unique subscribers and this is guaranteed by the
    //addListener and removeListener methods
    private ArrayList<IView> viewSubscribers = new ArrayList<IView>();
    //    private ArrayList<IModel> modelSubscribers = new ArrayList<IModel>();
    private ArrayList<ISwitchController> controllers = new ArrayList<ISwitchController>();

    IModel Player1;
    IModel Player2;

    //Keys, made this way inorder to rebind them in the future
    private int P1Right;
    private int P1Left;
    private int P2Right;
    private int P2Left;


    private String latestKey = " ";//init with blank
    @Override
    public String latestKeyPressed()
    {
        //Used for debug
        return latestKey;
    }

    @Override
    public boolean addListener(IView view) {

       if(!viewSubscribers.contains(view)){ //If we don't have a the view, add it
           viewSubscribers.add(view);
           return true;
       }

        return false;
    }

    @Override
    public boolean removeListener(IView view) {

        if(viewSubscribers.contains(view)){ //If we do have the view, remove it
            viewSubscribers.remove(view);
            return true;
        }
        return false;
    }

    @Override
    public boolean keyDown(int keycode) {
        /**
         *  This will be called when a key is pressed down (keyboard) by libgdx's InputProcessor
         */
        latestKey = Input.Keys.toString(keycode);

        if (keycode== P1Left){
            //Player1.moveLeft();
        }

        if (keycode== P1Right){
            //Player1.moveRight();
        }

        if (keycode== P2Left){
            //Player2.moveLeft();
        }

        if (keycode== P2Right){
            //Player2.moveRight();
        }

        if (keycode== Input.Keys.ESCAPE){

        }

        updateAllViews();

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
    private void updateAllViews() {
        //Call update to all views
        for (IView view : viewSubscribers) {
            view.update();
        }
    }

    @Override
    public void updateControllerList(ArrayList<ISwitchController> newList) {
        this.controllers = newList;
    }

    @Override
    public void setCurrentIP(int index) {
        Gdx.input.setInputProcessor(controllers.get(index));
    }

    //We don't need these inputs
    @Override
    public boolean keyUp(int keycode) {
        return false;
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

    public PlayerController(ArrayList<IView> views, ArrayList<ISwitchController> controllerList, IModel P1, IModel P2) {
        Gdx.input.setInputProcessor(this);

        this.Player1=P1;
        this.Player2=P2;

        this.P1Left = Input.Keys.A;
        this.P1Right = Input.Keys.D;
        this.P2Left = Input.Keys.J;
        this.P2Right = Input.Keys.L;

        this.controllers=controllerList;
        //this.modelSubscribers=models;
        this.viewSubscribers=views;
        //Makes it so LibGdx sends calls to this controller
        //(Adds the created PlayerController as a listener for LibGdx)
    }
}
