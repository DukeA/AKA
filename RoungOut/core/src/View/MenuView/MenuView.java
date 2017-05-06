package View.MenuView;

import View.ObjectView.BoardView;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextArea;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;

import static com.badlogic.gdx.graphics.Color.DARK_GRAY;

/**
 * Created by DukeA on 2017-04-28.
 */
public class MenuView  implements Screen, IHeadView{

    private  int WIDTH;
    private  int HEIGHT;
    private OrthographicCamera camera;
    private Stage stage;
    private SpriteBatch batch;
    private BitmapFont font;
    private Table table;
    private TextButton playButton;
    private TextButton optionsButton;
    private TextButton exitButton;
    private Skin skin;
    private TextArea textArea;


    public MenuView(int WIDTH, int HEIGHT) {
        this.WIDTH = WIDTH;
        this.HEIGHT = HEIGHT;
        this.skin = new Skin(Gdx.files.internal("uiskin.json"));
        this.font = new BitmapFont();
        this.stage = new Stage();
        this.camera = new OrthographicCamera();
    }

    @Override
    public void show() {
        table = new Table();
        table.setFillParent(true);
        table.top();

        textArea = new TextArea("ROUNGOUT",skin);


        playButton = new TextButton("Play"
                , skin,"default");
        playButton.setWidth(1000f);
        playButton.setHeight(30f);
        playButton.setPosition(WIDTH/2,HEIGHT/2);
        playButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {

            }
        });

        optionsButton = new TextButton("Options"
                ,skin,"default");
        optionsButton.setWidth(200f);
        optionsButton.setHeight(20f);
        optionsButton.setPosition(WIDTH/2,HEIGHT/2);
        optionsButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {

            }
        });

        exitButton = new TextButton("Exit",
                skin,"default");
        exitButton.setWidth(200f);
        exitButton.setHeight(20f);
        exitButton.setPosition(WIDTH/2,HEIGHT/2);
        exitButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {

            }
        });

        table.row();
        table.add(playButton).prefHeight(50)
                .prefWidth(20).width(500).padBottom(200)
                .setActorX(WIDTH/2);
        table.row();
        table.add(optionsButton).prefHeight(50)
                .prefWidth(20).width(500).padBottom(200).setActorX(WIDTH/2);
        table.row();
        table.add(exitButton).prefHeight(50)
                .prefWidth(20).width(500).padBottom(200).setActorX(WIDTH/2);
        table.row();


        stage.addActor(table);

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0,1f,0.5f,1f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch = new SpriteBatch();

        batch.begin();
        stage.act();
        stage.draw();
        font = new BitmapFont();
        font.draw(batch,"RoungOut",WIDTH/2, HEIGHT/2);
        font.setColor(DARK_GRAY);
        batch.end();

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

    @Override
    public BoardView createBoardView(int HEIGHT, int WIDTH) {
        return new BoardView(WIDTH,HEIGHT);
    }
    
}
