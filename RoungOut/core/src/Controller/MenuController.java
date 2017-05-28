package Controller;

import AbstractGameComponents.AMenuController;
import AbstractGameComponents.AGame;
import IViews.IViews;
import View.MenuView.OptionView;
import View.ObjectView.BoardView;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import java.util.ArrayList;

/**
 * @author Alex
 * Created on 2017-05-13.
 * Updated by Adam on 2017-05-25
 */
public class MenuController extends AMenuController implements IControllHandeling  {

    //List that contains the views that this controller interacts with
    //NOTE currently not in use, the views instead signals directly to this controller if they need it
    private ArrayList<IViews> viewSubscribers = new ArrayList<IViews>();

    //The handler that handles the switching of input processor
    private IHandler handler;

    //the enum that decides the typ of menu this is
    private final EnumIndexes typeOfMenu = EnumIndexes.MENU_CONTROLLER;



    private String latestKey = " ";//init with blank
    /**
     * Used for debugging purposes
     * @return Return the latest key pressed
     */
    public String latestKeyPressed()
    {
        //Used for debug
        return latestKey;
    }

    /**
     * View signals that the play-button was pressed
     * @param Width Width of the screen
     * @param Height Height of the screen
     * @param game the game we are currently using
     */
    public void playButtonIsPressed(int Width, int Height, AGame game){
        BoardView view = new BoardView(Width, Height,game);
        game.setScreen(view);

    }

    /**
     * View signals that the options-button was pressed
     * @param Width Width of the screen
     * @param Height Height of the screen
     * @param game the game we are currently using
     */
    public void optionsButtonIsPressed( int Width, int Height,AGame game) {
        OptionView view  = new OptionView(Width, Height,game);
        game.setScreen(view);
    }

    /**
     * The exit button was pressed, exit application
     */
    public void exitButtonIsPressed() {
        Gdx.app.exit();
    }

    /**
     *  This will be called when a key is pressed down (keyboard) by libgdx's InputProcessor
     * @param keycode the key that was pressed down on the keyboard
     * @return returns a boolean, this is used in the framework to toggle if it should propagate upwards thus
     * we return false
     */
    @Override
    public boolean keyDown(int keycode) {
        /**
         *  This will be called when a key is pressed down (keyboard) by libgdx's InputProcessor
         */
        latestKey = Input.Keys.toString(keycode);

        if (keycode== Input.Keys.ESCAPE){
            //IF ESC -> Go back to the game
            Gdx.app.exit();
        }

        return true;
    }

    //Helper method, code reuse
    //NOTE currently not in use, the views instead signals directly to this controller if they need it
    private void updateAllViews() {
        //Call update to all views
        for (IViews view : viewSubscribers) {
            view.update(Gdx.graphics.getDeltaTime());
        }
    }

    /**
     * Sets input processor to this instance
     */
    @Override
    public void changeInputProcessor() {
        Gdx.input.setInputProcessor(this);
    }

    /**
     * Getter, gets the enum that defines this menu (Same as controller)
     * @return the enum that defines the menu/controller
     */
    @Override
    public EnumIndexes getTypeOfMenu(){ return typeOfMenu;}

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

    /**
     * Constructor
     * @param views List of views to the controller
     * @param handler The handler that this controller obeys to
     */
    public MenuController(ArrayList<IViews> views, IHandler handler) {
        this.handler = handler;
        this.viewSubscribers=views;
    }
}
