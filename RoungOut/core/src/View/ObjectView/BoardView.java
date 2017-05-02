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
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.FitViewport;

import prototype.src.desktop.IScreen;



/**
 * Created by DukeA on 2017-04-28.
 */
public class BoardView implements Screen {

    private Stage stage;

    private BallView ballView;
    private PadView padview;
    private PadView padview2;

    private IBoard board;
    private ShapeRenderer shapeRenderer;
    private SpriteBatch batch;
    private final int WIDTH;
    private final int HEIGHT;


    public BoardView(int WIDTH,int HEIGHT) {
        this.WIDTH = WIDTH;
        this.HEIGHT = HEIGHT;
        this.stage = new Stage(new FitViewport(WIDTH,HEIGHT));

    }

    @Override
    public void show() {
        drawBoard();
        drawBall();
        drawPad();

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
        board =new Board(WIDTH, HEIGHT);
        batch = new SpriteBatch();
        shapeRenderer = new ShapeRenderer();
        Gdx.gl.glLineWidth(32);

        batch.begin();
        shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
        shapeRenderer.setColor(Color.BLACK);
        shapeRenderer.ellipse(board.getXPos(),board.getYPos(),
                board.getRadius(), (board.getRadius()* WIDTH/HEIGHT)-300);
        shapeRenderer.end();
        batch.end();

    }
    public void drawBall(){
        ballView = new BallView(WIDTH,HEIGHT);
        ballView.show();

    }
    public void drawPad() {
        padview = new PadView(WIDTH,HEIGHT,WIDTH/2+100,HEIGHT/2);
        padview2 = new PadView(WIDTH, HEIGHT,WIDTH/2 +110,HEIGHT/2);
        padview.show();
        padview2.show();
    }

}
