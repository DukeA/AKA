package View.ObjectView;

import View.MenuView.OptionView;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import javax.xml.soap.Text;

/**
 * Created by DukeA on 2017-05-17.
 */
public class InGameView implements Screen {

    private int WIDTH;
    private int HEIGHT;
    private BoardView view;
    private Game game;

    private Table table;
    private TextButton resumeButton;
    private TextButton exitButton;


    private OrthographicCamera camera;
    private Stage stage;
    private SpriteBatch batch;
    private Skin skin;


    public InGameView(int Width, int Height, Game game) {
        this.WIDTH = Width;
        this.HEIGHT = Height;
        this.game = game;

        this.skin = new Skin(Gdx.files.internal("uiskin.json"));
        this.stage = new Stage();
        this.camera = new OrthographicCamera(WIDTH, HEIGHT);
    }


    @Override
    public void show() {
        Gdx.input.setInputProcessor(stage);
        table = new Table();
        table.setFillParent(true);
        table.top();

        resumeButton = new TextButton("Resume", skin, "default");
        resumeButton.setWidth(200f);
        resumeButton.setHeight(30f);
        resumeButton.setPosition(WIDTH / 2, HEIGHT / 2);
        resumeButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.getScreen().hide();
                game.getScreen().dispose();
                view = new BoardView(WIDTH, HEIGHT, game);
                game.setScreen(view);
            }

        });

        exitButton = new TextButton("Quiet", skin, "default");
        exitButton.setWidth(200f);
        exitButton.setWidth(20f);
        exitButton.setPosition(WIDTH / 2, HEIGHT / 2);
        exitButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Gdx.app.exit();
            }


        });

        table.add(resumeButton).prefHeight(50)
                .prefWidth(20).width(500).padTop(100)
                .setActorX(WIDTH / 2);
        table.row();
        table.add(exitButton).prefHeight(50)
                .prefWidth(20).width(500).padTop(100).setActorX(WIDTH / 2);
        table.row();


        stage.addActor(table);
    }

        @Override
        public void render ( float delta){
            Gdx.gl.glClearColor(1f,0f,0f,1f);
            Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

            this.batch = new SpriteBatch();
            batch.begin();
            stage.draw();
            batch.end();
        }

        @Override
        public void resize ( int width, int height){

        }

        @Override
        public void pause () {

        }

        @Override
        public void resume () {

        }

        @Override
        public void hide () {

        }

        @Override
        public void dispose () {

        }
    }
