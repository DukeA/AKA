package View.ObjectView;


import Model.GameObjects.Ball;
import Model.GameObjects.IBall;
import Model.GameObjects.IPad;
import Model.GameObjects.Pad;
import Model.ViewObjects.Board;
import Model.ViewObjects.IBoard;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.FitViewport;

import prototype.src.desktop.IScreen;
import sun.security.provider.SHA;


/**
 * Created by DukeA on 2017-04-28.
 */
public class GameView implements Screen, IPad, IBall {

    private Stage stage;

    private Ball ball;
    private Pad pad;
    private Pad pad2;
    private IBoard board;
    private ShapeRenderer shapeRenderer;
    private ShapeRenderer shapeRenderer2;
    private ShapeRenderer shapeRenderer3;
    private ShapeRenderer shapeRenderer4;
    private SpriteBatch batch;
    private OrthographicCamera camera;
    private final int WIDTH;
    private final int HEIGHT;


    public GameView(int WIDTH, int HEIGHT) {
        this.WIDTH = WIDTH;
        this.HEIGHT = HEIGHT;
        camera = new OrthographicCamera(WIDTH, HEIGHT);
        camera.setToOrtho(false, WIDTH, HEIGHT);
        this.stage = new Stage(new FitViewport(WIDTH, HEIGHT));

    }

    @Override
    public void show() {


    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(1f, 1f, 1f, 1f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act(Gdx.graphics.getDeltaTime());
        batch = new SpriteBatch();
        batch.setProjectionMatrix(camera.combined);
        stage.draw();


        board = new Board(WIDTH, HEIGHT);
        shapeRenderer = new ShapeRenderer();
        Gdx.gl.glLineWidth(10);

        batch.begin();
        shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
        shapeRenderer.setColor(Color.BLACK);
        shapeRenderer.ellipse(WIDTH / 4, 0,
                board.getRadius() * (WIDTH / 4) / (HEIGHT / 4)
                , (board.getRadius() * (WIDTH / 4) / (HEIGHT / 4)));
        shapeRenderer.end();

        ball = createBall(WIDTH / 2, HEIGHT / 2);

        shapeRenderer2 = new ShapeRenderer();
        shapeRenderer2.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer2.setColor(Color.RED);
        shapeRenderer2.ellipse(
                (float) (ball.getX())
                , (float) (ball.getY())
                , (float) ((ball.getRadius() * ball.getY() / ball.getX()))
                , (float) (ball.getRadius() * ball.getY() / ball.getX()));
        shapeRenderer2.end();

        pad = createPad(WIDTH / 2 - 200, HEIGHT / 2 + 10);
        pad2 = createPad(WIDTH / 2 - 300, HEIGHT / 2 + 10);
        shapeRenderer3 = new ShapeRenderer();
        shapeRenderer3.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer3.setColor(Color.BLUE);
        shapeRenderer3.rect((float) pad.getPadXPos()
                , (float) pad.getPadYPos()
                , (float) pad.getWidth()
                , (float) pad.getLength());

        shapeRenderer3.end();
        shapeRenderer4 = new ShapeRenderer();
        shapeRenderer4.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer4.setColor(Color.LIME);
        shapeRenderer4.rect((float) pad2.getPadXPos(),
                (float) pad2.getPadYPos(),
                (float) pad2.getWidth(),
                (float) pad2.getLength());
        shapeRenderer4.end();
        batch.end();

        ball.move();

    }

    public void update(float delta) {
        stage.act(delta);
    }

    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width, height, true);
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
    }

    @Override
    public Pad createPad(int xPos, int yPos) {
        return new Pad(80f, 15f, xPos, yPos, 0);
    }

    @Override
    public Ball createBall(int xPos, int yPos) {
        return new Ball(WIDTH / 2, HEIGHT / 2, 60f, 0, 0);
    }
}
