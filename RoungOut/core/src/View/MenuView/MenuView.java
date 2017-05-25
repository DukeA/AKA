package View.MenuView;

import AbstractController.AMenuController;
import AbstractController.AOptionsController;
import AbstractGame.AGame;
import Controller.MenuController;
import Controller.OptionsController;
import View.ObjectView.BoardView;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;

/**
 * Created by DukeA on 2017-04-28.
 */
public class MenuView  implements IHeadView, Screen {

    private int WIDTH;
    private int HEIGHT;
    private AGame game;
    private OrthographicCamera camera;
    private Stage stage;
    private SpriteBatch batch;
    private BitmapFont font;
    private Table table;
    private Button playButton;
    private Button optionsButton;
    private Button exitButton;
    private TextureRegion Texture;
    private AMenuController controller;
    private Skin skin;


    public MenuView(int WIDTH, int HEIGHT, AGame game) {
        this.WIDTH = WIDTH;
        this.HEIGHT = HEIGHT;
        this.game = game;
        this.skin = new Skin(Gdx.files.internal("uiskin.json"));
        this.font = new BitmapFont();
        this.stage = new Stage();
        this.camera = new OrthographicCamera(WIDTH, HEIGHT);
        controller = new MenuController();


    }

    @Override
    public void show() {
    Gdx.input.setInputProcessor(stage);
        table = new Table();
        table.setFillParent(true);
        table.top();

        Texture = new TextureRegion(new Texture(Gdx.files.local(".\\core\\src\\Assets\\logo.png")));

        Image image = new Image(Texture);


        playButton = new TextButton("Play"
                , skin, "default");
        playButton.setWidth(200f);
        playButton.setHeight(30f);
        playButton.setPosition(WIDTH / 2, HEIGHT / 2);
        //playButton.addListener();



        optionsButton = new TextButton("Options"
                , skin, "default");
        optionsButton.setWidth(200f);
        optionsButton.setHeight(20f);
        optionsButton.setPosition(WIDTH / 2, HEIGHT / 2);
        //optionsButton.addListener();


        exitButton = new TextButton("Exit",
                skin, "default");
        exitButton.setWidth(200f);
        exitButton.setHeight(20f);
        exitButton.setPosition(WIDTH / 2, HEIGHT / 2);
        //exitButton.addListener();


        table.add(image).width(700).height(400);
        table.row();
        table.add(playButton).prefHeight(50)
                .prefWidth(20).width(500).padTop(100)
                .setActorX(WIDTH / 2);
        table.row();
        table.add(optionsButton).prefHeight(50)
                .prefWidth(20).width(500).padTop(100).setActorX(WIDTH / 2);
        table.row();
        table.add(exitButton).prefHeight(50)
                .prefWidth(20).width(500).padTop(100).setActorX(WIDTH / 2);
        table.row();


        stage.addActor(table);

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(1f, 0f, 0f, 1f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch = new SpriteBatch();

        batch.begin();
        stage.draw();
        batch.end();

        if (playButton.isPressed()) {
            controller.playButtonIsPressed(WIDTH,HEIGHT,game);
        }
        if (optionsButton.isPressed()) {
            controller.optionsButtonIsPressed(WIDTH,HEIGHT,game);
        }
        if (exitButton.isPressed()) {
            controller.exitButtonIsPressed();
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
        stage.dispose();
        batch.dispose();
    }

    //@Override
    public BoardView createBoardView(int HEIGHT, int WIDTH, AGame game) {

        return new BoardView(WIDTH, HEIGHT, game);
    }

    public OptionView createOptionView(int HEIGHT, int WIDTH ,AGame game) {

        return new OptionView(WIDTH, HEIGHT, game);
    }

}
