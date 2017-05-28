package Controller;

import AbstractGameComponents.AOptionsController;
import AbstractGameComponents.AGame;
import IViews.IViews;
import View.MenuView.MenuView;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import com.badlogic.gdx.scenes.scene2d.ui.CheckBox;
import com.badlogic.gdx.scenes.scene2d.ui.Label;

import java.util.ArrayList;

/**
 * Created by Alex on 2017-05-15.
 */

public class OptionsController extends AOptionsController implements IControllHandeling {

    //List that contains the views that this controller interacts with
    private ArrayList<IViews> optionsSubscribers = new ArrayList<IViews>();

    //The handler that handles the switching of input processor
    private IHandler handler;
    //the enum that decides the typ of menu this is
    private final EnumIndexes typeOfMenu = EnumIndexes.OPTIONS_CONTOLLER;




    private String latestKey = " ";//init with blank

    public String latestKeyPressed() {
        //Used for debug
        return latestKey;
    }

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

    public void boxClicked(int WIDTH, int HEIGHT, CheckBox box, AGame game) {
        ArrayList<Integer> newWH = new ArrayList<Integer>();
        Label value = box.getLabel();
        String width = String.valueOf(value.getText());
        String[] values = width.split("X");
        WIDTH = Integer.valueOf(values[0].trim());
        HEIGHT = Integer.valueOf(values[1].trim());
        game.resize(WIDTH, HEIGHT);
        newWH.add(0,WIDTH);
        newWH.add(1,HEIGHT);
        game.getRoungout(newWH.get(0),newWH.get(1));
    }

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

    public void escapeClicked(int Width, int Height, AGame game) {
        MenuView view = new MenuView(Width, Height, game);
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



    public OptionsController(ArrayList<IViews> views,IHandler handler) {
        this.optionsSubscribers = views;
        this.handler = handler;
    }

}
