package View.ObjectView;


import Model.ViewObjects.Board;
import Model.ViewObjects.IBoard;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.viewport.FitViewport;

import prototype.src.desktop.Roungout;


/**
 * Created by DukeA on 2017-04-28.
 */
public class BoardView implements Screen {

    private com.badlogic.gdx.scenes.scene2d.Stage stage;

    private BallView ballView;

    private IBoard board;
    private ShapeRenderer shapeRenderer;
    private SpriteBatch batch;
    private OrthographicCamera camera;
    private final int WIDTH;
    private final int HEIGHT;


    public BoardView(int WIDTH, int HEIGHT, OrthographicCamera camera) {
        this.WIDTH = WIDTH;
        this.HEIGHT = HEIGHT;
        this.camera = camera;
        this.stage = new com.badlogic.gdx.scenes.scene2d.Stage
                (new FitViewport(WIDTH, HEIGHT, camera));

    }

    @Override
    public void show() {
        drawBoard();
        drawBall();

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(1f, 1f, 1f, 1f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        update(delta);
        show();

        stage.act();
        stage.draw();
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

    public void drawBoard() {
        board =new Board(Roungout.WIDTH,Roungout.HEIGHT);
        batch = new SpriteBatch();
        shapeRenderer = new ShapeRenderer();
        Gdx.gl.glLineWidth(32);

        batch.begin();
        shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
        shapeRenderer.setColor(Color.BLACK);
        shapeRenderer.ellipse(board.getXPos()-900,board.getYPos()-450,
                board.getRadius(), board.getRadius()*Roungout.HEIGHT/Roungout.WIDTH);
        shapeRenderer.end();
        batch.end();

    }
    public void drawBall(){
        ballView = new BallView(WIDTH,HEIGHT,camera);
        ballView.show();

    }
    public void drawPad() {

    }
}
