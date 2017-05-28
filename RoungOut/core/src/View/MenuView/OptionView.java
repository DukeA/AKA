package View.MenuView;

import AbstractGameComponents.AOptionsController;
import AbstractGameComponents.AGame;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.utils.viewport.Viewport;

import java.util.ArrayList;

/**
 * @author Adam
 * Created on 2017-05-05.
 */
public class OptionView implements Screen{

    private int WIDTH;
    private int HEIGHT;
    private SpriteBatch batch;
    private Window resArea;
    private Window muteArea;
    private Window keyArea;
    private Skin skin;
    private Table table;
    private Label label;
    private Stage stage;
    private BitmapFont font;
    private AGame game;
    private AOptionsController controller;


    private  InputEvent event;

    private ArrayList<CheckBox> box;
    private ArrayList<CheckBox>muteBox;

    /**
     * The Construction method for OptionView.
     * The constructor  of the OptionViews takes
     * the size of the screen's WIDTH and HEIGHT
     * it also takes a referance of the game to set the screen.
     * @param WIDTH
     * @param HEIGHT
     * @param game
     */
    public OptionView(int WIDTH, int HEIGHT , AGame game) {
        this.WIDTH = WIDTH;
        this.HEIGHT = HEIGHT;
        this.game = game;
        this.skin = new Skin(Gdx.files.internal("uiskin.json"));
        this.font = new BitmapFont();
        this.stage = new Stage();
        this.batch = new SpriteBatch();
        this.event = new InputEvent();
        this.controller = game.getOptionsController();

    }

    /**
     * The show method is from the interface Screen
     * ,where the method shows what is supposed to be on screen.
     */
    @Override
    public void show() {
        Gdx.input.setInputProcessor(stage);
        table = new Table();
        table.setFillParent(true);
        table.top();
        resArea = new Window("Resolution",skin);
        muteArea = new Window("Mute",skin);
        keyArea = new Window("Key Change",skin);
        box = new ArrayList<CheckBox>();
        muteBox = new ArrayList<CheckBox>();
        label = new Label("Options",skin);


        table.add(label).padTop(150);
        label.setFontScale(1);
        label.setEllipsis(true);
        table.row();
        table.add(resArea).padTop(100).width(400).height(100);
        resArea.setMovable(false);
        keyArea.setMovable(false);
        muteArea.setMovable(false);
        for (int i =0; i<2; i++) {
            box.add(new CheckBox("",skin));
            resArea.add(box.get(i));
        }

        box.get(0).setText("1950 X 1080");


        box.get(1).setText("1680 X 1050");


        table.row();
        table.add(muteArea).width(400).height(100);
        for (int i =0; i<2; i++) {
            muteBox.add(new CheckBox("",skin));
            muteArea.add(muteBox.get(i));
        }
        muteBox.get(0).setText("Yes");


        muteBox.get(1).setText("No");



        table.row();
        table.add(keyArea).width(400).height(100);
        stage.addActor(table);

    }

    /**
     *  Render class is the thread in
     *  the view which updates the following Screen each time
     *  the render class runs.
     * @param delta
     */
    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(1f,0f,0f,1f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch = new SpriteBatch();

        batch.begin();
        stage.act();
        stage.draw();
        batch.end();

        for (CheckBox box: box) {
            if (box.isPressed()) {
                controller.boxClicked(WIDTH,HEIGHT,box,game);
                resize(WIDTH,HEIGHT);
            }
        }
        for (CheckBox muteBox : muteBox) {
            if (muteBox.isPressed()) {
                controller.muteBoxClicked(muteBox,game);

            }
        }
        if (Gdx.input.isKeyPressed(Input.Keys.ESCAPE)) {
            controller.escapeClicked(WIDTH,HEIGHT,game);
        }

    }

    /**
     * Unused method which were implmented from Screen
     */
    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
