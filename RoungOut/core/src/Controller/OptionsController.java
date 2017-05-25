package Controller;

import AbstractController.AOptionsController;
import AbstractGame.AGame;
import IViews.IViews;
import View.MenuView.MenuView;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.CheckBox;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import jdk.nashorn.internal.ir.IfNode;

import java.util.ArrayList;

/**
 * Created by Alex on 2017-05-15.
 */
public class OptionsController extends AOptionsController implements IControllHandeling {

    //List that contains the views that this controller interacts with
    private IViews OptionsView;

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
            handler.callSetNewInput(EnumIndexes.GAME_CONTROLLER);
            //IF ESC -> Go back to the game
            updateAllViews();
        }

        return true;
    }


    @Override
    public void clicked(InputEvent event, float x, float y) {

    }


    //Helper method, code reuse
    private void updateAllViews() {
        //Call update to all views
        OptionsView.update(Gdx.graphics.getDeltaTime());
    }

    public void boxClicked(int WIDTH, int HEIGHT, CheckBox box, AGame game) {
        Label value = box.getLabel();
        String width = String.valueOf(value.getText());
        String[] values = width.split("X");
        WIDTH = Integer.valueOf(values[0].trim());
        HEIGHT = Integer.valueOf(values[1].trim());
        game.resize(WIDTH, HEIGHT);

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


    public OptionsController(IHandler handler) {

        this.handler = handler;


    }

    public OptionsController() {

    }

}
