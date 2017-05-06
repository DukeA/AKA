package View.MenuView;

import View.IHeadView;
import View.ObjectView.BoardView;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextArea;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;

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
    private BoardView boardView;
    private Table table;
    private TextButton playButton;
    private TextButton optionsButton;
    private TextButton exitButton;
    private Skin skin;
    private AssetManager assetManager;
    private TextArea textArea;


    public MenuView(int WIDTH, int HEIGHT) {
        this.WIDTH = WIDTH;
        this.HEIGHT = HEIGHT;
        this.skin = new Skin(Gdx.files.internal("data/uiskin.json"));
        this.font = new BitmapFont();
        this.stage = new Stage();
        this.camera = new OrthographicCamera();
    }

    @Override
    public void show() {
        table = new Table();
        table.setFillParent(true);
        table.top();
        assetManager = new AssetManager();
        assetManager.load("gfx/uiskin.json", Skin.class);
        assetManager.finishLoading();


        playButton = new TextButton("Play"
                , assetManager.get("gfx/uiskin.json",Skin.class),"default");
        playButton.setWidth(200f);
        playButton.setHeight(20f);
        playButton.setPosition(WIDTH/2-300f,HEIGHT/2-10f);

        optionsButton = new TextButton("Options"
                ,assetManager.get("gfx/uiskin.json",Skin.class),"default");
        optionsButton.setWidth(200f);
        optionsButton.setHeight(20f);
        optionsButton.setPosition(WIDTH/2-200f,HEIGHT/2-10f);

        exitButton = new TextButton("Exit",
                assetManager.get("gfx/uiskin.json",Skin.class),"default");
        exitButton.setWidth(200f);
        exitButton.setHeight(20f);
        exitButton.setPosition(WIDTH/2-100f,HEIGHT/2-10f);

        table.add(playButton);
        table.row();
        table.add(optionsButton);
        table.row();
        table.add(exitButton);
        table.row();

        stage.addActor(table);

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0,0,1f,1f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch = new SpriteBatch();

        batch.begin();
        font.draw(batch,"RoungOut",WIDTH/2, HEIGHT/2);
        stage.act();
        stage.draw();
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

    @Override
    public OptionView createOptionView(int HEIGHT, int WIDTH) {
        return null;
    }
}
