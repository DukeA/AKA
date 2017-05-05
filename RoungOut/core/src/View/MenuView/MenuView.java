package View.MenuView;

import View.IHeadView;
import View.ObjectView.BoardView;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.Stage;

/**
 * Created by DukeA on 2017-04-28.
 */
public class MenuView  implements Screen, IHeadView{

    private  int WIDTH;
    private  int HEIGHT;
    private OrthographicCamera camera;
    private Stage stage;
    private ShapeRenderer shapeRenderer;
    private SpriteBatch batch;
    private BoardView boardView;

    public MenuView(int WIDTH, int HEIGHT) {
        this.WIDTH = WIDTH;
        this.HEIGHT = HEIGHT;
        this.stage = new Stage();
        this.camera = new OrthographicCamera();
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0,0,1f,1f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch = new SpriteBatch();
        shapeRenderer = new ShapeRenderer();


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

    @Override
    public BoardView createBoardView(int HEIGHT, int WIDTH) {
        return new BoardView(WIDTH,HEIGHT);
    }

    @Override
    public OptionView createOptionView(int HEIGHT, int WIDTH) {
        return null;
    }
}
