package View.MenuView;

import AbstractGame.AGame;
import Controller.OptionsController;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import java.util.ArrayList;

/**
 * Created by DukeA on 2017-05-05.
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


    private  InputEvent event;

    private ArrayList<CheckBox> box;
    private ArrayList<CheckBox>muteBox;

    public OptionView(int WIDTH, int HEIGHT , AGame game) {
        this.WIDTH = WIDTH;
        this.HEIGHT = HEIGHT;
        this.game = game;
        this.skin = new Skin(Gdx.files.internal("uiskin.json"));
        font = new BitmapFont();
        stage = new Stage();
        batch = new SpriteBatch();
        event = new InputEvent();

    }

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
        for (int i =0; i<4; i++) {
            box.add(new CheckBox("",skin));
            resArea.add(box.get(i));
        }
        box.get(0).setText("1950 X 1080");
        //box.get(0).addListener();

        box.get(1).setText("720 X 420");
        //box.get(1).addListener();

        box.get(2).setText("1280 X 720");
        //box.get(2).addListener();

        box.get(3).setText("1680 X 1050");
        //box.get(3).addListener();

        table.row();
        table.add(muteArea).width(400).height(100);
        for (int i =0; i<2; i++) {
            muteBox.add(new CheckBox("",skin));
            muteArea.add(muteBox.get(i));
        }
        muteBox.get(0).setText("Yes");
        //muteBox.get(0).addListener();

        muteBox.get(1).setText("No");
        //muteBox.get(1).addListener();



        table.row();
        table.add(keyArea).width(400).height(100);
        stage.addActor(table);

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(1f,0f,0f,1f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch = new SpriteBatch();

        batch.begin();
        stage.act();
        stage.draw();
        batch.end();
        for (int i =0; i<box.size(); i++) {
            if (box.get(i).isPressed()) {

            }
        }
        for (int i =0; i<muteBox.size(); i++) {
            if (muteBox.get(i).isPressed()) {

            }
        }

        if (Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)) {

        }

    }

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
