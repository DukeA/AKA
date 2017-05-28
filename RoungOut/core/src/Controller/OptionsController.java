package Controller;

import AbstractGameComponents.AOptionsController;
import AbstractGameComponents.AGame;
import IViews.IViews;
import View.MenuView.MenuView;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;

import com.badlogic.gdx.scenes.scene2d.ui.CheckBox;
import com.badlogic.gdx.scenes.scene2d.ui.Label;

import java.util.ArrayList;

/**
 * @author Alex Created on 2017-05-15.
 * Updated by Adam on 2017-05-25
 */

public class OptionsController extends AOptionsController implements IControllHandeling {

    //List that contains the views that this controller interacts with

    private ArrayList<IViews> optionsSubscribers = new ArrayList<IViews>();

    //The handler that handles the switching of input processor
    private IHandler handler;
    //the enum that decides the typ of menu this is
    private final EnumIndexes typeOfMenu = EnumIndexes.OPTIONS_CONTOLLER;

    private  ArrayList<Integer> setSize;


    private String latestKey = " ";//init with blank

    /**
     * Used for debug purposes
     * @return return the latest key pressed
     */
    public String latestKeyPressed() {
        //Used for debug
        return latestKey;
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

        if (keycode == Input.Keys.ESCAPE) {
            handler.callSetNewInput(EnumIndexes.MENU_CONTROLLER);
            //IF ESC -> Go back to the game
            updateAllViews();
        }

        return true;
    }



    //Helper method, code reuse
    private void updateAllViews() {
        //Call update to all views
        for (IViews view : optionsSubscribers) {
            view.update(Gdx.graphics.getDeltaTime());
        }
    }

    /**
     * when a box i clicked, th wiev calls this method, this method changes the screen size
     * @param WIDTH Width of the screen
     * @param HEIGHT Height od the screen
     * @param box the box that was clicked
     * @param game the game we are currently using
     * @return returns an array list [Width,Height] that is the new resolution
     *
     */
    public ArrayList<Integer>  boxClicked(int WIDTH, int HEIGHT, CheckBox box, AGame game) {
        ArrayList<Integer> newWH = new ArrayList<Integer>();
        Label value = box.getLabel();
        String width = String.valueOf(value.getText());
        String[] values = width.split("X");
        WIDTH = Integer.valueOf(values[0].trim());
        HEIGHT = Integer.valueOf(values[1].trim());
        game.resize(WIDTH, HEIGHT);
        newWH.add(0,WIDTH);
        newWH.add(1,HEIGHT);
        return setSize =  game.setSize(newWH.get(0), newWH.get(1));
    }

    /**
     * Mute method, called when the mute button is pressed
     * @param box The mox that corresponds to the mute mox
     * @param game the game we are currently using
     * @return The boolean that toggles the mute
     */
    public boolean muteBoxClicked(CheckBox box, AGame game) {
        boolean muteValue;
        Label value = box.getLabel();
        String buttonValue = String.valueOf(value);
        if (buttonValue.equals("yes")) {
            muteValue = true;
        } else {
            muteValue = false;
        }
        return muteValue;
    }

    /**
     * If we click the esc-button we return to the main menu
     *
     * @param game the game we are currently using
     */
    public void escapeClicked( AGame game) {
        MenuView view = new MenuView(setSize.get(0), setSize.get(1), game);
        game.setScreen(view);
    }

    @Override
    public void changeInputProcessor() {
        Gdx.input.setInputProcessor(this);
    }

    @Override
    public EnumIndexes getTypeOfMenu() {
        return typeOfMenu;
    }

    //We don't need these inputs
    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        return false;
    }

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
    public OptionsController(ArrayList<IViews> views,IHandler handler) {
        this.optionsSubscribers = views;
        this.handler = handler;
    }

}
