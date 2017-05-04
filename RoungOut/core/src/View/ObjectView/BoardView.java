package View.ObjectView;


import Model.GameObjects.Board;
import Model.GameObjects.IBoard;
import View.IView;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

import prototype.src.desktop.IScreen;



/**
 * Created by DukeA on 2017-04-28.
 */
public class BoardView implements Screen , IViews{


    private IViews views;
    private BallView ballView;
    private PadView padView;
    private IBoard board;
    private ShapeRenderer shapeRenderer;
    private SpriteBatch batch;
    private final int WIDTH;
    private final int HEIGHT;


    public BoardView(int WIDTH,int HEIGHT) {
        this.WIDTH = WIDTH;
        this.HEIGHT = HEIGHT;
        padView = views.createPad(WIDTH,HEIGHT);
        ballView = views.createBall(WIDTH,HEIGHT);

    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(1f, 1f, 1f, 1f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        update(delta);

        board =new Board(WIDTH, HEIGHT);
        batch = new SpriteBatch();
        shapeRenderer = new ShapeRenderer();
        Gdx.gl.glLineWidth(32);

        batch.begin();
        shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
        shapeRenderer.setColor(Color.BLACK);
        shapeRenderer.ellipse(WIDTH/4,25,
                board.getRadius()*(WIDTH/4)/(HEIGHT/4)
                , (board.getRadius()*(WIDTH/4)/(HEIGHT/4)));
        shapeRenderer.end();
        batch.end();
        padView.render(Gdx.graphics.getDeltaTime());
        ballView.render(Gdx.graphics.getDeltaTime());


    }

    public void update(float delta) {

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

        shapeRenderer.dispose();
    }

    @Override
    public BallView createBall(int xPos, int yPos) {
        return new BallView(xPos, yPos);
    }

    @Override
    public PadView createPad(int xPos, int yPos) {
        return new PadView(xPos, yPos);
    }
}
