package prototype.src;

import Objects.Board;
import Objects.IBoard;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.Disposable;
import com.sun.media.jfxmediaimpl.MediaDisposer;

/**
 * Created by DukeA on 2017-03-31.
 */
public class LevelRenderer implements Disposable {
    private OrthographicCamera camera;
    private SpriteBatch batch;
    private LevelController levelController;
    private ShapeRenderer renderer;

    private final int WIDTH = Gdx.graphics.getWidth();
    private final int HEIGHT = Gdx.graphics.getHeight();

    public LevelRenderer( LevelController levelController) {
        this.levelController = levelController;
        init();
    }
    public void init() {
        camera = new OrthographicCamera(5.0f,5.0f);
        camera.position.set(0,0,0);
        camera.update();
    }
    public void render() {
        renderObjects();
    }
    public void renderObjects() {
        int LineWidth =5;
        IBoard board = new Board(WIDTH,HEIGHT);
        renderer = new ShapeRenderer();
        renderer.begin(ShapeRenderer.ShapeType.Line);
        Gdx.gl20.glLineWidth(LineWidth/ camera.zoom);
        renderer.setColor(Color.BLACK);
        renderer.circle(board.getxPos(),board.getYPos(),board.getRadius());
        renderer.end();

    }
    public void reszie(int width, int height) {
        camera.viewportWidth=((5.0f/height)* height);
        camera.update();
    }

    @Override
    public void dispose() {
        batch.dispose();
    }
}
